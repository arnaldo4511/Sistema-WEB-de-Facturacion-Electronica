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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import pe.modelo.pojo.EstadoDocumentoVenta;
import pe.modelo.pojo.ResumenVentas;
import pe.modelo.pojo.ResumenVentasGrupo;

public class ComunicacionBaja {

    private Document document;
    private String fileName;
    private BillService_Service service;
    private BillService port;
    private String rootFile;
    private String recIdGeneral;
    private String usrIdGeneral;
    private ResumenVentas resumenVentas;

    public ComunicacionBaja() {
    }

    public void generarCaso(ResumenVentas resumenVentas) {
        this.resumenVentas = resumenVentas;
        generarDom();
        generarDocumento();
        generarXml();
        firmarXml();
        generarZip();
        inicializarServicioWeb();
        agregarHeaderSecurity();
        sendSummary();
    }

    public void consultarEstadoProceso(ResumenVentas resumenVentas) {
        this.resumenVentas=resumenVentas;
        inicializarServicioWeb();
        agregarHeaderSecurity();
        String nameFileZip = resumenVentas.getEmpresa().getEntidad().getDocumento() + "-"
                + resumenVentas.getTipo() + "-"
                + resumenVentas.getFechaEmision().toString().replace("-", "")
                + "-" + resumenVentas.getNumero();
        String recTicketProceso = resumenVentas.getTicketSunat();
        getStatus(nameFileZip, recTicketProceso);
    }

    private void generarDom() {
        try {
            DocumentBuilderFactory factoty = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factoty.newDocumentBuilder();
            factoty.setNamespaceAware(true);
            document = builder.newDocument();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ComunicacionBaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generarDocumento() {
        //recIdGeneral = resumenDiario.getString("recId");
        fileName = pe.modelo.Configuracion.rutaComunicacionesDeBajas
                + resumenVentas.getEmpresa().getEntidad().getDocumento() + "-"
                + resumenVentas.getTipo() + "-"
                + (pe.modelo.Configuracion.simpleDateFormat.format(resumenVentas.getFechaEmision())).replace("-", "")
                + "-" + resumenVentas.getNumero();
        String empRazonSocial = pe.modelo.Configuracion.reemplazarCaracteresParaSunat(resumenVentas.getEmpresa().getEntidad().getNombre());
        String empRuc = resumenVentas.getEmpresa().getEntidad().getDocumento();
        String empTipoEntidad = resumenVentas.getEmpresa().getEntidad().getTipoDocumentoEntidad().getCodigo();
        String tprCodigo = resumenVentas.getTipo();
        String recFecha = resumenVentas.getFechaEmision().toString();
        String recFechaEmision = resumenVentas.getFechaEmision().toString();
        String recNumero = resumenVentas.getNumero();

        String ubl = "2.0";
        String vDocomento = "1.0";
        String idDoc = tprCodigo + "-" + recFecha.replace("-", "") + "-" + recNumero;

        org.w3c.dom.Element voidedDocuments = document.createElement("VoidedDocuments");
        voidedDocuments.setAttribute("xmlns", "urn:sunat:names:specification:ubl:peru:schema:xsd:VoidedDocuments-1");
        voidedDocuments.setAttribute("xmlns:cac", "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2");
        voidedDocuments.setAttribute("xmlns:cbc", "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2");
        voidedDocuments.setAttribute("xmlns:ds", "http://www.w3.org/2000/09/xmldsig#");
        voidedDocuments.setAttribute("xmlns:ext", "urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2");
        voidedDocuments.setAttribute("xmlns:sac", "urn:sunat:names:specification:ubl:peru:schema:xsd:SunatAggregateComponents-1");
        voidedDocuments.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        document.appendChild(voidedDocuments);

        //INICIO 18. Total valor de venta - operaciones gravadas
        org.w3c.dom.Element extUBLExtensions = document.createElement("ext:UBLExtensions");
        voidedDocuments.appendChild(extUBLExtensions);

        //FIN 18º
        //INICIO 2. Firma Digital. Obligatorio.
        org.w3c.dom.Element extUBLExtension_2 = document.createElement("ext:UBLExtension");
        extUBLExtensions.appendChild(extUBLExtension_2);

        org.w3c.dom.Element extExtensionContent_2 = document.createElement("ext:ExtensionContent");
        extUBLExtension_2.appendChild(extExtensionContent_2);

        //INICIO 36. Versión del UBL
        org.w3c.dom.Element cbcUBLVersionID = document.createElement("cbc:UBLVersionID");
        cbcUBLVersionID.appendChild(document.createTextNode(ubl));
        voidedDocuments.appendChild(cbcUBLVersionID);
        //FIN 36

        //INICIO 37. Versión de la estructura del documento
        org.w3c.dom.Element cbcCustomizationID = document.createElement("cbc:CustomizationID");
        cbcCustomizationID.appendChild(document.createTextNode(vDocomento));
        voidedDocuments.appendChild(cbcCustomizationID);
        //FIN 37

        //INICIO 8. Numeración, conformada por serie y número correlativo
        org.w3c.dom.Element cbcID_3 = document.createElement("cbc:ID");
        cbcID_3.appendChild(document.createTextNode(idDoc));
        voidedDocuments.appendChild(cbcID_3);
        //FIN 8

        //INICIO 1. Fecha de Referencia. Obligatorio.
        org.w3c.dom.Element cbcReferenceDate = document.createElement("cbc:ReferenceDate");
        cbcReferenceDate.appendChild(document.createTextNode(recFecha));
        voidedDocuments.appendChild(cbcReferenceDate);
        //FIN 1

        //INICIO 1. Fecha de emisión. Obligatorio.
        org.w3c.dom.Element cbcIssueDate = document.createElement("cbc:IssueDate");
        cbcIssueDate.appendChild(document.createTextNode(recFechaEmision));
        voidedDocuments.appendChild(cbcIssueDate);
        //FIN 1

        //
        org.w3c.dom.Element cacSignature = document.createElement("cac:Signature");
        voidedDocuments.appendChild(cacSignature);

        org.w3c.dom.Element cbcID_4 = document.createElement("cbc:ID");
        cbcID_4.appendChild(document.createTextNode(pe.modelo.Configuracion.nombreFirma));
        cacSignature.appendChild(cbcID_4);

        org.w3c.dom.Element cacSignatoryParty = document.createElement("cac:SignatoryParty");
        cacSignature.appendChild(cacSignatoryParty);

        org.w3c.dom.Element cacPartyIdentification = document.createElement("cac:PartyIdentification");
        cacSignatoryParty.appendChild(cacPartyIdentification);

        org.w3c.dom.Element cbcID_5 = document.createElement("cbc:ID");
        cbcID_5.appendChild(document.createTextNode(empRuc));
        cacPartyIdentification.appendChild(cbcID_5);

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
        cbcURI.appendChild(document.createTextNode("#" + pe.modelo.Configuracion.nombreFirma));
        cacExternalReference.appendChild(cbcURI);
        //

        //INICIO 6. Número de RUC. Obligatorio.
        org.w3c.dom.Element cacAccountingSupplierParty = document.createElement("cac:AccountingSupplierParty");
        voidedDocuments.appendChild(cacAccountingSupplierParty);

        org.w3c.dom.Element cbcCustomerAssignedAccountID = document.createElement("cbc:CustomerAssignedAccountID");
        cbcCustomerAssignedAccountID.appendChild(document.createTextNode(empRuc));
        cacAccountingSupplierParty.appendChild(cbcCustomerAssignedAccountID);

        org.w3c.dom.Element cbcAdditionalAccountID = document.createElement("cbc:AdditionalAccountID");
        cbcAdditionalAccountID.appendChild(document.createTextNode(empTipoEntidad));
        cacAccountingSupplierParty.appendChild(cbcAdditionalAccountID);
        //INICIO 3. Apellidos y nombres o denominación o razón social
        org.w3c.dom.Element cacParty = document.createElement("cac:Party");
        cacAccountingSupplierParty.appendChild(cacParty);

        //FIN 5
        org.w3c.dom.Element cacPartyLegalEntity = document.createElement("cac:PartyLegalEntity");
        cacParty.appendChild(cacPartyLegalEntity);

        org.w3c.dom.Element cbcRegistrationName = document.createElement("cbc:RegistrationName");
        cbcRegistrationName.appendChild(document.createCDATASection(empRazonSocial));
        cacPartyLegalEntity.appendChild(cbcRegistrationName);
        //FIN 3
        //FIN 6
        Integer cont = 0;
        for (ResumenVentasGrupo resumenVentasGrupo : resumenVentas.getResumenVentasGrupos()) {
            voidedDocuments.appendChild(contruirDetalle(resumenVentasGrupo, Integer.toString(cont + 1)));
        }
    }

    private org.w3c.dom.Element contruirDetalle(ResumenVentasGrupo resumenVentasGrupo, String nroDetalle) {
        String tdcCodigo = resumenVentasGrupo.getTipoDocumentoVenta().getCodigo();
        String redSerie = resumenVentasGrupo.getPuntoVentaSerie().getCodigo();
        String redInicioDocumento = resumenVentasGrupo.getInicioDocumentoVenta();
        String redDescripcion = resumenVentasGrupo.getDescripcion();
        org.w3c.dom.Element sacVoidedDocumentsLine = document.createElement("sac:VoidedDocumentsLine");
        //invoice.appendChild(cacInvoiceLine);

        org.w3c.dom.Element cbcLineID = document.createElement("cbc:LineID");
        cbcLineID.appendChild(document.createTextNode(nroDetalle));
        sacVoidedDocumentsLine.appendChild(cbcLineID);

        org.w3c.dom.Element cbcDocumentTypeCode = document.createElement("cbc:DocumentTypeCode");
        cbcDocumentTypeCode.appendChild(document.createTextNode(tdcCodigo));
        sacVoidedDocumentsLine.appendChild(cbcDocumentTypeCode);

        org.w3c.dom.Element sacDocumentSerialID = document.createElement("sac:DocumentSerialID");
        sacDocumentSerialID.appendChild(document.createTextNode(redSerie));
        sacVoidedDocumentsLine.appendChild(sacDocumentSerialID);

        org.w3c.dom.Element sacDocumentNumberID = document.createElement("sac:DocumentNumberID");
        sacDocumentNumberID.appendChild(document.createTextNode(redInicioDocumento));
        sacVoidedDocumentsLine.appendChild(sacDocumentNumberID);

        org.w3c.dom.Element sacVoidReasonDescription = document.createElement("sac:VoidReasonDescription");
        sacVoidReasonDescription.appendChild(document.createTextNode(redDescripcion));
        sacVoidedDocumentsLine.appendChild(sacVoidReasonDescription);

        return sacVoidedDocumentsLine;
    }

    private void generarXml() {
        try {
            TransformerFactory factoria = TransformerFactory.newInstance();
            Transformer transformer = factoria.newTransformer();

            transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            document.getDocumentElement().normalize();
            Source source = new DOMSource(document);
            File file = new File(fileName + ".xml");
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            Result result = new StreamResult(pw);

            transformer.transform(source, result);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(ComunicacionBaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ComunicacionBaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(ComunicacionBaja.class.getName()).log(Level.SEVERE, null, ex);
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
            ks.load(new FileInputStream(pe.modelo.Configuracion.rutaCertificado + pe.modelo.Configuracion.nombreCertificado), pe.modelo.Configuracion.claveCertificado.toCharArray());
            KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(pe.modelo.Configuracion.usuarioLlave, new KeyStore.PasswordProtection(pe.modelo.Configuracion.claveLlave.toCharArray()));
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

            XMLSignature signature = fac.newXMLSignature(si, ki, null, pe.modelo.Configuracion.nombreFirma, null);

            signature.sign(dsc);

            OutputStream os = new FileOutputStream(fileName + ".xml");
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer();
            trans.transform(new DOMSource(doc), new StreamResult(os));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ComunicacionBaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(ComunicacionBaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyStoreException ex) {
            Logger.getLogger(ComunicacionBaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ComunicacionBaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger(ComunicacionBaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnrecoverableEntryException ex) {
            Logger.getLogger(ComunicacionBaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ComunicacionBaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MarshalException ex) {
            Logger.getLogger(ComunicacionBaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLSignatureException ex) {
            Logger.getLogger(ComunicacionBaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ComunicacionBaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(ComunicacionBaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(ComunicacionBaja.class.getName()).log(Level.SEVERE, null, ex);
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
        handlerChain.add(new WSSecurityHeaderSOAPHandler(pe.modelo.Configuracion.usuarioSol, pe.modelo.Configuracion.claveSol));
        bindingProvider.getBinding().setHandlerChain(handlerChain);
    }

    private void sendSummary() {
        try {
            FileDataSource file = new FileDataSource(fileName + ".zip");
            javax.activation.DataHandler contentFile = new javax.activation.DataHandler(file);
            String retorno = port.sendSummary(contentFile.getName(), contentFile);
            String recTicketProceso = retorno;
            EstadoDocumentoVenta estadoDocumentoVenta = new EstadoDocumentoVenta();
            estadoDocumentoVenta.setCodigo("PES");
            resumenVentas.setEstadoDocumentoVenta(estadoDocumentoVenta);
            resumenVentas.setTicketSunat(recTicketProceso);
            getStatus(contentFile.getName().replace(".zip", ""), recTicketProceso);
        } catch (javax.xml.ws.soap.SOAPFaultException ex) {
            String error = ex.getFault().getFaultCodeAsQName().getLocalPart();
            System.out.println("error:" + error);
        }
    }

    private void getStatus(String nameFileZip, String ticket) {
        try {
            StatusResponse retorno = port.getStatus(ticket);
            String recEstadoProceso = retorno.getStatusCode();
            System.out.println("recEstadoProceso:" + recEstadoProceso);
            resumenVentas.setEstadoSunat(recEstadoProceso);
            EstadoDocumentoVenta estadoDocumentoVenta = new EstadoDocumentoVenta();
            if (recEstadoProceso.equals("0")) {
                estadoDocumentoVenta.setCodigo("VAL");
                resumenVentas.setEstadoDocumentoVenta(estadoDocumentoVenta);
            } else if (recEstadoProceso.equals("99")) {
                estadoDocumentoVenta.setCodigo("REC");
                resumenVentas.setEstadoDocumentoVenta(estadoDocumentoVenta);
            }
            FileOutputStream retornofactura = new FileOutputStream(pe.modelo.Configuracion.rutaCDRsComunicacionesDeBajas + "R-" + nameFileZip + ".zip");
            retornofactura.write(retorno.getContent());
        } catch (javax.xml.ws.soap.SOAPFaultException ex) {
            String error = ex.getFault().getFaultCodeAsQName().getLocalPart();
            System.out.println(error);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ComunicacionBaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ComunicacionBaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
