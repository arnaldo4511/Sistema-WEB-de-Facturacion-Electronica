/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dto.ventas.facturacion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.FileDataSource;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import pe.modelo.pojo.ResumenVentas;
import pe.modelo.pojo.ResumenVentasGrupo;

public class ResumenBoletas {

    private Document document;
    private String fileName;
    private BillService_Service service;
    private BillService port;
    private String rootFile;
    private String usrIdGeneral;
    private String recIdGeneral;

    public ResumenBoletas() {
    }

    public void generarCaso(ResumenVentas resumenVentas) {
        generarDom();
        generarDocumento(resumenVentas);
        generarXml();
        firmarXml();
        generarZip();
        inicializarServicioWeb();
        agregarHeaderSecurity();
        sendSummary();

    }

    public void consultarEstadoProceso(String recId, String nameFileZip, String recTicketProceso, String usrId) {
        inicializarServicioWeb();
        agregarHeaderSecurity();
        getStatus(recId, nameFileZip, recTicketProceso, usrId);
    }

    private void generarDom() {
        try {
            DocumentBuilderFactory factoty = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factoty.newDocumentBuilder();
            factoty.setNamespaceAware(true);
            document = builder.newDocument();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ResumenBoletas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generarDocumento(ResumenVentas resumenVentas) {
        fileName = pe.modelo.Configuracion.rutaComunicacionesDeBajas
                + resumenVentas.getEmpresa().getEntidad().getDocumento() + "-"
                + resumenVentas.getTipo() + "-"
                + (pe.modelo.Configuracion.simpleDateFormat.format(resumenVentas.getFechaEmision())).replace("-", "") + "-"
                + resumenVentas.getNumero();
        //recIdGeneral = resumenDiario.getString("recId");

        String empRazonSocial = resumenVentas.getEmpresa().getEntidad().getNombre();
        String empRuc = resumenVentas.getEmpresa().getEntidad().getDocumento();
        String empTipoEntidad = resumenVentas.getEmpresa().getEntidad().getTipoDocumentoEntidad().getCodigo();
        String tprCodigo = resumenVentas.getTipo();
        String recFecha = pe.modelo.Configuracion.simpleDateFormat.format(resumenVentas.getFechaEmision());
        String recFechaEmision = pe.modelo.Configuracion.simpleDateFormat.format(resumenVentas.getFechaEmision());
        String recNumero = resumenVentas.getNumero();

        String ubl = "2.0";
        String vDocomento = "1.0";
        String idDoc = tprCodigo + "-" + recFecha.replace("-", "") + "-" + recNumero;

        org.w3c.dom.Element SummaryDocuments = document.createElement("SummaryDocuments");
        SummaryDocuments.setAttribute("xmlns", "urn:sunat:names:specification:ubl:peru:schema:xsd:SummaryDocuments-1");
        SummaryDocuments.setAttribute("xmlns:cac", "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2");
        SummaryDocuments.setAttribute("xmlns:cbc", "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2");
        SummaryDocuments.setAttribute("xmlns:ds", "http://www.w3.org/2000/09/xmldsig#");
        SummaryDocuments.setAttribute("xmlns:ext", "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2");
        SummaryDocuments.setAttribute("xmlns:sac", "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1");
        SummaryDocuments.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        document.appendChild(SummaryDocuments);

        org.w3c.dom.Element extUBLExtensions = document.createElement("ext:UBLExtensions");
        SummaryDocuments.appendChild(extUBLExtensions);

        //2. Firma Digital. Obligatorio.
        org.w3c.dom.Element extUBLExtension = document.createElement("ext:UBLExtension");
        extUBLExtensions.appendChild(extUBLExtension);

        org.w3c.dom.Element extExtensionContent = document.createElement("ext:ExtensionContent");
        extUBLExtension.appendChild(extExtensionContent);

        //36. Versión del UBL
        org.w3c.dom.Element cbcUBLVersionID = document.createElement("cbc:UBLVersionID");
        cbcUBLVersionID.appendChild(document.createTextNode(ubl));
        SummaryDocuments.appendChild(cbcUBLVersionID);

        //37. Versión de la estructura del documento
        org.w3c.dom.Element cbcCustomizationID = document.createElement("cbc:CustomizationID");
        cbcCustomizationID.appendChild(document.createTextNode(vDocomento));
        SummaryDocuments.appendChild(cbcCustomizationID);

        //8 Numeración, conformada por serie y número correlativo
        org.w3c.dom.Element cbcID = document.createElement("cbc:ID");
        cbcID.appendChild(document.createTextNode(idDoc));
        SummaryDocuments.appendChild(cbcID);

        //
        org.w3c.dom.Element cbcReferenceDate = document.createElement("cbc:ReferenceDate");
        cbcReferenceDate.appendChild(document.createTextNode(recFechaEmision));
        SummaryDocuments.appendChild(cbcReferenceDate);

        //1. Fecha de emisión. Obligatorio.
        org.w3c.dom.Element cbcIssueDate = document.createElement("cbc:IssueDate");
        cbcIssueDate.appendChild(document.createTextNode(recFechaEmision));
        SummaryDocuments.appendChild(cbcIssueDate);

        //
        org.w3c.dom.Element cacSignature = document.createElement("cac:Signature");
        SummaryDocuments.appendChild(cacSignature);

        org.w3c.dom.Element cbcID_2 = document.createElement("cbc:ID");
        cbcID_2.appendChild(document.createTextNode("signatureGRIFO"));
        cacSignature.appendChild(cbcID_2);

        org.w3c.dom.Element cacSignatoryParty = document.createElement("cac:SignatoryParty");
        cacSignature.appendChild(cacSignatoryParty);

        org.w3c.dom.Element cacPartyIdentification = document.createElement("cac:PartyIdentification");
        cacSignatoryParty.appendChild(cacPartyIdentification);

        org.w3c.dom.Element cbcID_3 = document.createElement("cbc:ID");
        cbcID_3.appendChild(document.createTextNode(empRuc));
        cacPartyIdentification.appendChild(cbcID_3);

        org.w3c.dom.Element cacPartyName = document.createElement("cac:PartyName");
        cacSignatoryParty.appendChild(cacPartyName);

        org.w3c.dom.Element cbcName = document.createElement("cbc:Name");
        cbcName.appendChild(document.createCDATASection(empRazonSocial));
        cacPartyName.appendChild(cbcName);

        org.w3c.dom.Element cacDigitalSignatureAttachment = document.createElement("cac:DigitalSignatureAttachment");
        cacSignature.appendChild(cacDigitalSignatureAttachment);

        org.w3c.dom.Element cacExternalReference = document.createElement("cac:ExternalReference");
        cacDigitalSignatureAttachment.appendChild(cacExternalReference);

        org.w3c.dom.Element cbcURI = document.createElement("cbc:URI");
        cbcURI.appendChild(document.createTextNode("#signatureGRIFO"));
        cacExternalReference.appendChild(cbcURI);

        //3. Apellidos y nombres, denominación o razón social
        //4. Nombre Comercial
        //5. Domicilio fiscal
        //6. Número de RUC
        org.w3c.dom.Element cacAccountingSupplierParty = document.createElement("cac:AccountingSupplierParty");
        SummaryDocuments.appendChild(cacAccountingSupplierParty);

        org.w3c.dom.Element cbcCustomerAssignedAccountID = document.createElement("cbc:CustomerAssignedAccountID");
        cbcCustomerAssignedAccountID.appendChild(document.createTextNode(empRuc));
        cacAccountingSupplierParty.appendChild(cbcCustomerAssignedAccountID);

        org.w3c.dom.Element cbcAdditionalAccountID = document.createElement("cbc:AdditionalAccountID");
        cbcAdditionalAccountID.appendChild(document.createTextNode(empTipoEntidad));
        cacAccountingSupplierParty.appendChild(cbcAdditionalAccountID);

        org.w3c.dom.Element cacParty = document.createElement("cac:Party");
        cacAccountingSupplierParty.appendChild(cacParty);

        org.w3c.dom.Element cacPartyLegalEntity = document.createElement("cac:PartyLegalEntity");
        cacParty.appendChild(cacPartyLegalEntity);

        org.w3c.dom.Element cbcRegistrationName = document.createElement("cbc:RegistrationName");
        cbcRegistrationName.appendChild(document.createCDATASection(empRazonSocial));
        cacPartyLegalEntity.appendChild(cbcRegistrationName);

        Integer cont = 0;
        for (ResumenVentasGrupo resumenVentasGrupo : resumenVentas.getResumenVentasGrupos()) {
            SummaryDocuments.appendChild(contruirDetalle(resumenVentasGrupo, Integer.toString(cont + 1)));
        }
    }

    private org.w3c.dom.Element contruirDetalle(ResumenVentasGrupo resumenVentasGrupo, String nroDetalle) {
        //
        String tdcCodigo = resumenVentasGrupo.getTipoDocumentoVenta().getCodigo();
        String redSerie = resumenVentasGrupo.getPuntoVentaSerie().getCodigo();
        String redInicioDocumento = resumenVentasGrupo.getInicioDocumentoVenta();
        String redFinDocumento = resumenVentasGrupo.getFinDocumentoVenta();
        String redTotalVenta = pe.modelo.Configuracion.decimalFormat.format(resumenVentasGrupo.getTotal());
        String redTotalGrabado = pe.modelo.Configuracion.decimalFormat.format(resumenVentasGrupo.getTotalGrabado());
        String redTotalExonerado = pe.modelo.Configuracion.decimalFormat.format(0);
        String redTotalInafecto = pe.modelo.Configuracion.decimalFormat.format(0);
        String redIndicadorOtrosCargos = Boolean.toString(false);
        String redTotalOtrosCargos = pe.modelo.Configuracion.decimalFormat.format(0);
        String redTotalIsc = pe.modelo.Configuracion.decimalFormat.format(0);
        String redTotalIgv = pe.modelo.Configuracion.decimalFormat.format(resumenVentasGrupo.getTotalIgv());
        String monCodigo = resumenVentasGrupo.getMoneda().getCodigo();

        org.w3c.dom.Element sacSummaryDocumentsLine = document.createElement("sac:SummaryDocumentsLine");

        org.w3c.dom.Element cbcLineID = document.createElement("cbc:LineID");
        cbcLineID.appendChild(document.createTextNode(nroDetalle));
        sacSummaryDocumentsLine.appendChild(cbcLineID);

        org.w3c.dom.Element cbcDocumentTypeCode = document.createElement("cbc:DocumentTypeCode");
        cbcDocumentTypeCode.appendChild(document.createTextNode(tdcCodigo));
        sacSummaryDocumentsLine.appendChild(cbcDocumentTypeCode);

        org.w3c.dom.Element sacDocumentSerialID = document.createElement("sac:DocumentSerialID");
        sacDocumentSerialID.appendChild(document.createTextNode(redSerie));
        sacSummaryDocumentsLine.appendChild(sacDocumentSerialID);

        org.w3c.dom.Element sacStartDocumentNumberID = document.createElement("sac:StartDocumentNumberID");
        sacStartDocumentNumberID.appendChild(document.createTextNode(redInicioDocumento));
        sacSummaryDocumentsLine.appendChild(sacStartDocumentNumberID);

        org.w3c.dom.Element sacEndDocumentNumberID = document.createElement("sac:EndDocumentNumberID");
        sacEndDocumentNumberID.appendChild(document.createTextNode(redFinDocumento));
        sacSummaryDocumentsLine.appendChild(sacEndDocumentNumberID);

        org.w3c.dom.Element sacTotalAmount = document.createElement("sac:TotalAmount");
        sacTotalAmount.setAttribute("currencyID", monCodigo);
        sacTotalAmount.appendChild(document.createTextNode(redTotalVenta));
        sacSummaryDocumentsLine.appendChild(sacTotalAmount);
        //
        org.w3c.dom.Element sacBillingPayment = document.createElement("sac:BillingPayment");
        sacSummaryDocumentsLine.appendChild(sacBillingPayment);

        org.w3c.dom.Element cbcPaidAmount = document.createElement("cbc:PaidAmount");
        cbcPaidAmount.setAttribute("currencyID", monCodigo);
        cbcPaidAmount.appendChild(document.createTextNode(redTotalGrabado));
        sacBillingPayment.appendChild(cbcPaidAmount);

        org.w3c.dom.Element cbcInstructionID = document.createElement("cbc:InstructionID");
        cbcInstructionID.appendChild(document.createTextNode("01"));
        sacBillingPayment.appendChild(cbcInstructionID);
        //
        //
        org.w3c.dom.Element sacBillingPayment_2 = document.createElement("sac:BillingPayment");
        sacSummaryDocumentsLine.appendChild(sacBillingPayment_2);

        org.w3c.dom.Element cbcPaidAmount_2 = document.createElement("cbc:PaidAmount");
        cbcPaidAmount_2.setAttribute("currencyID", monCodigo);
        cbcPaidAmount_2.appendChild(document.createTextNode(redTotalExonerado));
        sacBillingPayment_2.appendChild(cbcPaidAmount_2);

        org.w3c.dom.Element cbcInstructionID_2 = document.createElement("cbc:InstructionID");
        cbcInstructionID_2.appendChild(document.createTextNode("02"));
        sacBillingPayment_2.appendChild(cbcInstructionID_2);
        //
        //
        org.w3c.dom.Element sacBillingPayment_3 = document.createElement("sac:BillingPayment");
        sacSummaryDocumentsLine.appendChild(sacBillingPayment_3);

        org.w3c.dom.Element cbcPaidAmount_3 = document.createElement("cbc:PaidAmount");
        cbcPaidAmount_3.setAttribute("currencyID", monCodigo);
        cbcPaidAmount_3.appendChild(document.createTextNode(redTotalInafecto));
        sacBillingPayment_3.appendChild(cbcPaidAmount_3);

        org.w3c.dom.Element cbcInstructionID_3 = document.createElement("cbc:InstructionID");
        cbcInstructionID_3.appendChild(document.createTextNode("03"));
        sacBillingPayment_3.appendChild(cbcInstructionID_3);
        //

        org.w3c.dom.Element cacAllowanceCharge = document.createElement("cac:AllowanceCharge");
        sacSummaryDocumentsLine.appendChild(cacAllowanceCharge);

        org.w3c.dom.Element cbcChargeIndicator = document.createElement("cbc:ChargeIndicator");
        cbcChargeIndicator.appendChild(document.createTextNode(redIndicadorOtrosCargos));
        cacAllowanceCharge.appendChild(cbcChargeIndicator);

        org.w3c.dom.Element cbcAmount = document.createElement("cbc:Amount");
        cbcAmount.setAttribute("currencyID", monCodigo);
        cbcAmount.appendChild(document.createTextNode(redTotalOtrosCargos));
        cacAllowanceCharge.appendChild(cbcAmount);

        //
        org.w3c.dom.Element cacTaxTotal = document.createElement("cac:TaxTotal");
        sacSummaryDocumentsLine.appendChild(cacTaxTotal);

        org.w3c.dom.Element cbcTaxAmount = document.createElement("cbc:TaxAmount");
        cbcTaxAmount.setAttribute("currencyID", monCodigo);
        cbcTaxAmount.appendChild(document.createTextNode(redTotalIsc));
        cacTaxTotal.appendChild(cbcTaxAmount);

        org.w3c.dom.Element cacTaxSubtotal = document.createElement("cac:TaxSubtotal");
        cacTaxTotal.appendChild(cacTaxSubtotal);

        org.w3c.dom.Element cbcTaxAmount_2 = document.createElement("cbc:TaxAmount");
        cbcTaxAmount_2.setAttribute("currencyID", monCodigo);
        cbcTaxAmount_2.appendChild(document.createTextNode(redTotalIsc));
        cacTaxSubtotal.appendChild(cbcTaxAmount_2);

        org.w3c.dom.Element cacTaxCategory = document.createElement("cac:TaxCategory");
        cacTaxSubtotal.appendChild(cacTaxCategory);

        org.w3c.dom.Element cacTaxScheme = document.createElement("cac:TaxScheme");
        cacTaxCategory.appendChild(cacTaxScheme);

        org.w3c.dom.Element cbcID_4 = document.createElement("cbc:ID");
        cbcID_4.appendChild(document.createTextNode("2000"));
        cacTaxScheme.appendChild(cbcID_4);

        org.w3c.dom.Element cbcName_2 = document.createElement("cbc:Name");
        cbcName_2.appendChild(document.createTextNode("ISC"));
        cacTaxScheme.appendChild(cbcName_2);

        org.w3c.dom.Element cbcTaxTypeCode = document.createElement("cbc:TaxTypeCode");
        cbcTaxTypeCode.appendChild(document.createTextNode("EXC"));
        cacTaxScheme.appendChild(cbcTaxTypeCode);

        //
        org.w3c.dom.Element cacTaxTotal_2 = document.createElement("cac:TaxTotal");
        sacSummaryDocumentsLine.appendChild(cacTaxTotal_2);

        org.w3c.dom.Element cbcTaxAmount_3 = document.createElement("cbc:TaxAmount");
        cbcTaxAmount_3.setAttribute("currencyID", monCodigo);
        cbcTaxAmount_3.appendChild(document.createTextNode(redTotalIgv));
        cacTaxTotal_2.appendChild(cbcTaxAmount_3);

        org.w3c.dom.Element cacTaxSubtotal_2 = document.createElement("cac:TaxSubtotal");
        cacTaxTotal_2.appendChild(cacTaxSubtotal_2);

        org.w3c.dom.Element cbcTaxAmount_4 = document.createElement("cbc:TaxAmount");
        cbcTaxAmount_4.setAttribute("currencyID", monCodigo);
        cbcTaxAmount_4.appendChild(document.createTextNode(redTotalIgv));
        cacTaxSubtotal_2.appendChild(cbcTaxAmount_4);

        org.w3c.dom.Element cacTaxCategory_2 = document.createElement("cac:TaxCategory");
        cacTaxSubtotal_2.appendChild(cacTaxCategory_2);

        org.w3c.dom.Element cacTaxScheme_2 = document.createElement("cac:TaxScheme");
        cacTaxCategory_2.appendChild(cacTaxScheme_2);

        org.w3c.dom.Element cbcID_5 = document.createElement("cbc:ID");
        cbcID_5.appendChild(document.createTextNode("1000"));
        cacTaxScheme_2.appendChild(cbcID_5);

        org.w3c.dom.Element cbcName_3 = document.createElement("cbc:Name");
        cbcName_3.appendChild(document.createTextNode("IGV"));
        cacTaxScheme_2.appendChild(cbcName_3);

        org.w3c.dom.Element cbcTaxTypeCode_2 = document.createElement("cbc:TaxTypeCode");
        cbcTaxTypeCode_2.appendChild(document.createTextNode("VAT"));
        cacTaxScheme_2.appendChild(cbcTaxTypeCode_2);

        return sacSummaryDocumentsLine;
    }

    private void generarXml() {
        try {
            TransformerFactory factoria = TransformerFactory.newInstance();
            Transformer transformer = factoria.newTransformer();

            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            document.getDocumentElement().normalize();
            Source source = new DOMSource(document);
            File file = new File(fileName + ".xml");
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            Result result = new StreamResult(pw);

            transformer.transform(source, result);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(ResumenBoletas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ResumenBoletas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(ResumenBoletas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void firmarXml() {
        try {
            XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
            Reference ref = fac.newReference("", fac.newDigestMethod(DigestMethod.SHA1, null),
                    Collections.singletonList(fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)), null, null);
            SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null),
                    fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                    Collections.singletonList(ref));
            KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(new FileInputStream(rootFile + "/WEB-INF/grifoAeropuerto610.jks"), "123456".toCharArray());
            KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry("le-4d8bcdce-1552-493b-8fe7-52522be66422", new KeyStore.PasswordProtection("CDERFVBGTYH".toCharArray()));
            X509Certificate cert = (X509Certificate) keyEntry.getCertificate();

            KeyInfoFactory kif = fac.getKeyInfoFactory();
            List x509Content = new ArrayList();
            x509Content.add(cert.getSubjectX500Principal().getName());
            x509Content.add(cert);
            X509Data xd = kif.newX509Data(x509Content);
            KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd));

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            Document doc = dbf.newDocumentBuilder().parse(new FileInputStream(fileName + ".xml"));

            DOMSignContext dsc = new DOMSignContext(keyEntry.getPrivateKey(), doc.getElementsByTagName("ext:ExtensionContent").item(0));

            dsc.setDefaultNamespacePrefix("ds");

            XMLSignature signature = fac.newXMLSignature(si, ki, null, "signatureGRIFO", null);

            signature.sign(dsc);

            OutputStream os = new FileOutputStream(fileName + ".xml");
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer();
            trans.transform(new DOMSource(doc), new StreamResult(os));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ResumenBoletas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(ResumenBoletas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyStoreException ex) {
            Logger.getLogger(ResumenBoletas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ResumenBoletas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger(ResumenBoletas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverableEntryException ex) {
            Logger.getLogger(ResumenBoletas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ResumenBoletas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MarshalException ex) {
            Logger.getLogger(ResumenBoletas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLSignatureException ex) {
            Logger.getLogger(ResumenBoletas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ResumenBoletas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(ResumenBoletas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(ResumenBoletas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void generarZip() {
        try {
            File f = new File(fileName + ".zip");
            if (f.exists() && !f.isDirectory()) {
                f.delete();
            }
            ZipFile zipFile = new ZipFile(fileName + ".zip");

            ArrayList filesToAdd = new ArrayList();
            filesToAdd.add(new File(fileName + ".xml"));

            //addFileToZip// .add(new File("dummy/"));
            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); // set compression method to deflate compression

            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
            zipFile.addFiles(filesToAdd, parameters);

            //zipFile.addFolder("dummy", parameters);
        } catch (ZipException e) {
            e.printStackTrace();
        }

    }

    private void inicializarServicioWeb() {
        service = new BillService_Service();
        port = service.getBillServicePort();
    }

    private void agregarHeaderSecurity() {
        BindingProvider bindingProvider = (BindingProvider) port;
        @SuppressWarnings("request-headers")
        List<Handler> handlerChain = new ArrayList<Handler>();
        handlerChain.add(new WSSecurityHeaderSOAPHandler("20311644786FACTU610", "factelec16"));
        bindingProvider.getBinding().setHandlerChain(handlerChain);
    }

    private void sendSummary() {
        try {
            FileDataSource file = new FileDataSource(fileName + ".zip");
            javax.activation.DataHandler contentFile = new javax.activation.DataHandler(file);
            String ticket = port.sendSummary(contentFile.getName(), contentFile);
            //System.out.println(ticket);
            String recTicketProceso = ticket;
            //resumenDAO = new resumenDAO();
            //resumenDAO.actualizarEnvioSunat(recIdGeneral, recTicketProceso, usrIdGeneral);
            getStatus(recIdGeneral, contentFile.getName().replace(".zip", ""), ticket, usrIdGeneral);
        } catch (javax.xml.ws.soap.SOAPFaultException ex) {
            String error = ex.getFault().getFaultCodeAsQName().getLocalPart();
            System.out.println(error);
        }
    }

    private void getStatus(String recId, String nameFileZip, String ticket, String usrId) {
        try {
            StatusResponse retorno = port.getStatus(ticket);
            String recEstadoProceso = retorno.getStatusCode();
            //resumenDAO = new resumenDAO();
            //resumenDAO.actualizarEstadoDocumentoSunat(recId, recEstadoProceso, usrId);
            if (recEstadoProceso.equals("0")) {
                //resumenDAO.actualizarDocumentosResumen(recId, "ENV", usrId, "5");
            }
            byte[] content = retorno.getContent();
            FileOutputStream retornofactura = new FileOutputStream(pe.modelo.Configuracion.rutaCDRsResumenesDeBoletas + "R-" + nameFileZip);
            retornofactura.write(content);
        } catch (javax.xml.ws.soap.SOAPFaultException ex) {
            String error = ex.getFault().getFaultCodeAsQName().getLocalPart();
            System.out.println(error);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ResumenBoletas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ResumenBoletas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
