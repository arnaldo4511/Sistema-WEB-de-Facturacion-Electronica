/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.ventas;

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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import pe.modelo.dto.ParametroDto;
import pe.modelo.dao.HibernateUtil;
import pe.modelo.pojo.DocumentoVenta;
import pe.modelo.pojo.DocumentoVentaDetalle;
import pe.modelo.pojo.TipoDocumentoVenta;
import pe.modelo.dto.ventas.NotificacionDto;

/**
 *
 * @author octavio
 */
public class DocumentoVentaDao implements IDocumentoVentaDao {

    @Override
    public void crear(DocumentoVenta documentoVenta) {
        try {
            documentoVenta.setFechaCreacion(new Date());
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.save(documentoVenta);
            for (DocumentoVentaDetalle documentoVentaDetalle : documentoVenta.getDocumentoVentaDetalles()) {
                documentoVentaDetalle.setDocumentoVenta(documentoVenta);
                documentoVentaDetalle.setFechaCreacion(new Date());
                sesion.save(documentoVentaDetalle);
            }
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editar(DocumentoVenta documentoVenta) {
        try {
            documentoVenta.setFechaModificacion(new Date());
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.update(documentoVenta);
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public DocumentoVenta buscar(long id) {
        DocumentoVenta documentoVenta = null;
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            documentoVenta = (DocumentoVenta) sesion.load(DocumentoVenta.class, id);
            Hibernate.initialize(documentoVenta);
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return documentoVenta;
    }

    @Override
    public JasperPrint generarReporte(DocumentoVenta documentoVenta) {
        JasperPrint rptDocumentoVenta = null;
        try {
            JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("../../reportes/ventas/rptDocumentoVenta.jasper"));
            Map parameters = new HashMap();
            Locale locale = new Locale("es", "PE");
            parameters.put(JRParameter.REPORT_LOCALE, locale);
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(documentoVenta.getDocumentoVentaDetalles());
            rptDocumentoVenta = JasperFillManager.fillReport(report, parameters, ds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rptDocumentoVenta;
    }

    @Override
    public void eliminar(long id) {
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            String hql = "delete from DocumentoVenta where id= :id";
            sesion.createQuery(hql).setLong("id", id).executeUpdate();
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DocumentoVenta> listar(ParametroDto[] parametros) {
        List<DocumentoVenta> lista = null;
        try {
            System.out.println("parametros[0] "+parametros[0].getValor());
            System.out.println("parametros[1] "+parametros[1].getValor());
            System.out.println("parametros[2] "+parametros[2].getValor());
            System.out.println("parametros[3] "+parametros[3].getValor());
            System.out.println("parametros[4] "+parametros[4].getValor());
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DocumentoVenta.class);
            if (!parametros[0].getValor().equals("")) {
                detachedCriteria.add(Property.forName("tipoDocumentoVenta.codigo").eq(parametros[0].getValor()));
            }
            if (!parametros[4].getValor().equals("")) {
                detachedCriteria.add(Property.forName("puntoVentaSerie.codigo").eq(parametros[4].getValor()));
            }
            detachedCriteria.add(Property.forName("numero").like("%" + parametros[3].getValor() + "%"));
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            lista = detachedCriteria.getExecutableCriteria(sesion).setFirstResult(Integer.parseInt(parametros[1].getValor())).setMaxResults(Integer.parseInt(parametros[2].getValor())).addOrder(Property.forName("tipoDocumentoVenta.codigo").desc())/*.addOrder(Property.forName("numero").asc())*/.list();
            //List<DocumentoVenta> lista2 = sesion.createCriteria(DocumentoVenta.class).list();
            //Query query = sesion.createQuery("FROM DocumentoVenta order by id");
            //lista = query.list();
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public long totalDocumentoVentas(ParametroDto[] parametros) {
        long total = 0;
        try {
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DocumentoVenta.class);
            if (!parametros[0].getValor().equals("")) {
                detachedCriteria.add(Property.forName("tipoDocumentoVenta.codigo").eq(parametros[0].getValor()));
            }
            detachedCriteria.add(Property.forName("numero").like("%" + parametros[3].getValor() + "%"));

            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            total = (long) detachedCriteria.getExecutableCriteria(sesion).setProjection(Projections.rowCount()).uniqueResult();
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    public List<DocumentoVenta> autocompletar(String criterio) {
        List<DocumentoVenta> lista = null;
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            Query query = sesion.createQuery("FROM DocumentoVenta CLI WHERE UPPER(CLI.entidad.nombre) LIKE UPPER(:criterio) OR UPPER(CLI.entidad.documento) LIKE UPPER(:criterio) order by id").setString("criterio", "%" + criterio + "%");
            lista = query.list();
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void enviarCliente(NotificacionDto notificacionDto) {
        try {
            DocumentoVenta documentoVenta = buscar(notificacionDto.getIdDocumentoVenta());
            String nombreDocumento = documentoVenta.getPuntoVentaSerie().getCodigo() + "-" + documentoVenta.getNumero();

            String saludo = "Estimado Cliente, se le envia una " + documentoVenta.getTipoDocumentoVenta().getNombre() + ".";
            String asunto = documentoVenta.getTipoDocumentoVenta().getNombre();
            asunto += " " + nombreDocumento;
            asunto += " | " + documentoVenta.getEmpresa().getEntidad().getNombre();
            String from = documentoVenta.getEmpresa().getCorreoAdmin();
            String pass = documentoVenta.getEmpresa().getContrasenhiaCorreo();
            String host = "smtp.gmail.com";
            Properties properties = System.getProperties();
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            properties.put("mail.smtp.user", from);
            properties.put("mail.smtp.password", pass);
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.setProperty("mail.user", from);
            properties.setProperty("mail.password", pass);
            javax.mail.Session session = javax.mail.Session.getDefaultInstance(properties);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            if (notificacionDto.getTipo().equals("CLI"))/*CLI=CLIENTE;PER=PERSONALIZADO*/ {
                if (documentoVenta.getCliente().getEntidad().getCorreoElectronico1() != null) {
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(documentoVenta.getCliente().getEntidad().getCorreoElectronico1()));
                }
                if (documentoVenta.getCliente().getEntidad().getCorreoElectronico2() != null) {
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(documentoVenta.getCliente().getEntidad().getCorreoElectronico2()));
                }
                if (documentoVenta.getCliente().getEntidad().getCorreoElectronico3() != null) {
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(documentoVenta.getCliente().getEntidad().getCorreoElectronico3()));
                }
            } else {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(notificacionDto.getEmail()));
            }
            message.setSubject(asunto);
            BodyPart messageBodyPart = new MimeBodyPart();
            String mensaje = saludo + "<br><br>\n"
                    + "<div style='width: 600px;background:#3F51B5;color: #ffffff;margin: 0 auto;text-align: center;padding: 5px;border-radius: 15px;'>\n"
                    + "            <img style='width: 160px;height: 204px;' src='http://www.gepp.pe/LOGOS/" + documentoVenta.getEmpresa().getLogo() + "'><h1>" + nombreDocumento + "</h1>\n"
                    + "            <h1>No. " + documentoVenta.getPuntoVentaSerie().getCodigo() + "-" + documentoVenta.getNumero() + "</h1>\n"
                    + "            <div>Fecha de emisión: " + documentoVenta.getFechaEmision() + "</div>\n"
                    + "            <div>Fecha de vencimiento: " + documentoVenta.getFechaVencimiento() + "</div>\n"
                    + "            <h1>S/ " + documentoVenta.getTotal() + "</h1>\n"
                    + "            <div>Se adjunta en este mensaje el documento electrónico en formatos PDF y XML(para boletas solo se envia un PDF).</div>\n"
                    + "            <div>La resepresentación impresa en PDF puede imprimirse y usarse como un documento emitido</div>\n"
                    + "            <div>de manera tradicional.</div>\n"
                    + "        </div><br>"
                    + "        <div style='width: 600px;margin: 0 auto;'>\n"
                    + "            También puedes ver el documento visitando el siguiente link.\n"
                    + "        </div>\n"
                    + "        <a href=\"http://intranet.gepp.pe:8080/gepp_intranet/\" target=\"_blank\" style='margin: 0 auto;width: 600px; background:#4caf50;color:#ffffff;text-align:center;text-decoration:none;display:block;border-radius: 15px;'>\n"
                    + "            <h2>VER " + nombreDocumento + "</h2>\n"
                    + "        </a>\n"
                    + "        <center><b>O</b></center>\n"
                    + "        <a href=\"http://intranet.gepp.pe:8080/gepp_intranet/\" target=\"_blank\" style='margin: 0 auto;width: 600px;display:block;text-align:center;'>\n"
                    + "            <h2>http://intranet.gepp.pe:8080/gepp_intranet/</h2>\n"
                    + "        </a><br>\n"
                    + "Saludos,<br>\n"
                    + documentoVenta.getEmpresa().getEntidad().getNombre();
            messageBodyPart.setContent(mensaje, "text/html");

            Multipart multipart = new MimeMultipart();

            multipart.addBodyPart(messageBodyPart);
            JasperPrint print = generarReporte(documentoVenta);
            byte[] bytes = JasperExportManager.exportReportToPdf(print);
            messageBodyPart = new MimeBodyPart();
            DataSource pdf = new ByteArrayDataSource(bytes, "application/pdf");

            messageBodyPart.setDataHandler(new DataHandler(pdf));
            messageBodyPart.setFileName(nombreDocumento + ".pdf");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);

            // Send message
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());

            transport.close();
        } catch (MessagingException ex) {
            ex.printStackTrace();
        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }

}
