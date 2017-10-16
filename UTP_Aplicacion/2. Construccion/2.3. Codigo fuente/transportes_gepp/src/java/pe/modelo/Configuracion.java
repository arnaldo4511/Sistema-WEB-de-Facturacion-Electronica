/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author octavio
 */
public class Configuracion {

    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public static String usuarioSol = "20454442793FACTELE1";
    public static String claveSol = "factelec17";
    //
    public static String nombreCertificado = "certificado.jks";
    public static String claveCertificado = "123456";
    public static String usuarioLlave = "le-9697410e-d425-4444-8b12-ee7ee507a134";
    public static String claveLlave = "E32SW2AQ1Z2";
    public static String nombreFirma = "signatureTRANSMAP";

    public static String rutaFacturas = "\\\\192.168.0.220\\Facturacion\\FacturacionElectronica\\20454442793\\Facturas\\";
    public static String rutaCDRsFacturas = "\\\\192.168.0.220\\Facturacion\\FacturacionElectronica\\20454442793\\Facturas\\CDRs\\";
    public static String rutaNotasDeCredito = "\\\\192.168.0.220\\Facturacion\\FacturacionElectronica\\20454442793\\NotasDeCredito\\";
    public static String rutaCDRsNotasDeCredito = "\\\\192.168.0.220\\Facturacion\\FacturacionElectronica\\20454442793\\NotasDeCredito\\CDRs\\";
    public static String rutaResumenesDeBoletas = "\\\\192.168.0.220\\Facturacion\\FacturacionElectronica\\20454442793\\ResumenesDeBoletas\\";
    public static String rutaCDRsResumenesDeBoletas = "\\\\192.168.0.220\\Facturacion\\FacturacionElectronica\\20454442793\\ResumenesDeBoletas\\CDRs\\";
    public static String rutaComunicacionesDeBajas = "\\\\192.168.0.220\\Facturacion\\FacturacionElectronica\\20454442793\\ComunicacionesDeBajas\\";
    public static String rutaCDRsComunicacionesDeBajas = "\\\\192.168.0.220\\Facturacion\\FacturacionElectronica\\20454442793\\ComunicacionesDeBajas\\CDRs\\";
    public static String rutaImagenProducto = "\\\\192.168.0.220\\Public\\REPOSITORIO-SISTEMA-GEPP\\PRODUCTOS\\";
    public static String rutaCertificado = "\\\\192.168.0.220\\Facturacion\\Certificados\\";

    public static Boolean pingSunat() {
        Boolean conStatus = false;
        try {
            URL u = new URL("https://www.sunat.gob.pe/ol-ti-itcpfegem/billService?wsdl");
            HttpsURLConnection huc = (HttpsURLConnection) u.openConnection();
            huc.connect();
            conStatus = true;
            huc.disconnect();
        } catch (Exception e) {
            conStatus = false;
        }
        return conStatus;
    }

    public static Boolean pingServidorDeCorreo() {
        Boolean conStatus = false;
        try {
            URL u = new URL("http://www.smtp-gmail.com/");
            HttpURLConnection huc = (HttpURLConnection) u.openConnection();
            huc.connect();
            conStatus = true;
            huc.disconnect();
        } catch (Exception e) {
            conStatus = false;
            //e.printStackTrace();
        }
        return conStatus;
    }

    public static String reemplazarCaracteresParaSunat(String dato) {
        String datoReem = dato;
        datoReem = datoReem.replace("ñ", "n");
        datoReem = datoReem.replace("Ñ", "N");
        return datoReem;
    }

    public static double redondearDecimales(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado = (resultado - parteEntera) * Math.pow(10, numeroDecimales);
        resultado = Math.round(resultado);
        resultado = (resultado / Math.pow(10, numeroDecimales)) + parteEntera;
        return resultado;
    }
}
