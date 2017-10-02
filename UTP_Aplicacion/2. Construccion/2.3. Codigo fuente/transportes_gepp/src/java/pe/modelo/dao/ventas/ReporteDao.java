/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.ventas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import pe.modelo.dao.publico.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.query.JRHibernateQueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRLoader;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import pe.modelo.dto.ParametroDto;
import pe.modelo.dao.HibernateUtil;
import pe.modelo.dto.ventas.ListaDocumentoVentaDto;
import pe.modelo.pojo.DocumentoVenta;
import pe.modelo.pojo.DocumentoVentaDetalle;
import pe.modelo.pojo.TipoDocumentoVenta;
import pe.modelo.dto.ventas.NotificacionDto;
import pe.modelo.dto.ventas.AnulacionDto;
import pe.modelo.dto.ventas.facturacion.ComunicacionBaja;
import pe.modelo.dto.ventas.facturacion.Factura;
import pe.modelo.pojo.EstadoDocumentoVenta;
import pe.modelo.pojo.ResumenVentas;
import pe.modelo.pojo.ResumenVentasGrupo;
import pe.modelo.pojo.ResumenVentasGrupoVenta;
import pe.modelo.pojo.funcion.FnConDocumentoVenta;
import pe.modelo.pojo.funcion.FnVentaXPlacaAnual;
import pe.modelo.pojo.vista.VwRptVentaXPlacaAnual;
import pe.modelo.pojo.vista.VwSelDocumentoVenta;

/**
 *
 * @author octavio
 */
public class ReporteDao implements IReporteDao {

    @Override
    public JasperPrint generarVentaXPlacaAnual(Integer anhio, Long idEmpresa) {
        JasperPrint rptDocumentoVenta = null;
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            Query query = sesion.getNamedQuery("FnVentaXPlacaAnual");
            List<FnVentaXPlacaAnual> fnVentaXPlacaAnual = query.setLong("par_id_empresa", idEmpresa)
                    .setInteger("par_anhio", anhio).list();
            sesion.getTransaction().commit();
            sesion.close();
            JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("../../reporte/ventas/rptVentaXPlacaAnual.jasper"));
            Map parameters = new HashMap();
            Locale locale = new Locale("es", "PE");
            parameters.put(JRParameter.REPORT_LOCALE, locale);
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(fnVentaXPlacaAnual);
            rptDocumentoVenta = JasperFillManager.fillReport(report, parameters, ds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rptDocumentoVenta;
    }

    @Override
    public JasperPrint generarConDocumentoVenta(Long idEmpresa, Integer anhio, Integer mes, Integer diaInicio, Integer diaFin, Integer inicioCorrelativo) {
        JasperPrint rptDocumentoVenta = null;
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            Query query = sesion.getNamedQuery("FnConDocumentoVenta");
            List<FnConDocumentoVenta> fnConDocumentoVentas = query.setLong("id_empresa", idEmpresa)
                    .setInteger("anhio", anhio)
                    .setInteger("mes", mes)
                    .setInteger("dia_inicio", diaInicio)
                    .setInteger("dia_fin", diaFin)
                    .setInteger("inicio_correlativo", inicioCorrelativo).list();
            sesion.getTransaction().commit();
            sesion.close();
            JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("../../reporte/ventas/rptVentasConcar.jasper"));
            Map parameters = new HashMap();
            Locale locale = new Locale("es", "PE");
            parameters.put(JRParameter.REPORT_LOCALE, locale);
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(fnConDocumentoVentas);
            rptDocumentoVenta = JasperFillManager.fillReport(report, parameters, ds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rptDocumentoVenta;
    }
}
