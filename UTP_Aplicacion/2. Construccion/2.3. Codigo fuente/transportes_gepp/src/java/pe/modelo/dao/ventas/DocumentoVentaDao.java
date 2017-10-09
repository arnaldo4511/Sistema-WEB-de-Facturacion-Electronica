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
import pe.modelo.dto.ventas.facturacion.NotaCredito;
import pe.modelo.pojo.EstadoDocumentoVenta;
import pe.modelo.pojo.ResumenVentas;
import pe.modelo.pojo.ResumenVentasGrupo;
import pe.modelo.pojo.ResumenVentasGrupoVenta;
import pe.modelo.pojo.vista.VwSelDocumentoVenta;

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
            documentoVenta = buscar(documentoVenta.getId());

            enviarSunat(documentoVenta);

            NotificacionDto notificacionDto = new NotificacionDto();
            notificacionDto.setIdDocumentoVenta(documentoVenta.getId());
            notificacionDto.setDocumentoVenta(documentoVenta);
            notificacionDto.setTipo("CLI");

            enviarCliente(notificacionDto);
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
            JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("../../reporte/ventas/rptDocumentoVenta.jasper"));
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
    public ListaDocumentoVentaDto listar(ParametroDto[] parametros) {
        ListaDocumentoVentaDto listaDocumentoVentaDto = new ListaDocumentoVentaDto();
        List<VwSelDocumentoVenta> lista = null;
        long nroDocumentoVentas = 0;
        double totalFacturas = 0;
        double totalBoletas = 0;
        double totalNotaCreditos = 0;
        double totalNotaDebitos = 0;
        double totalPendienteEnvioSunat = 0;
        double totalPendienteComunicacionBajas = 0;
        Integer currentPage = 0;
        Integer itemsPerPage = 0;
        Date fechaDesde = null;
        Date fechaHasta = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        try {
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(VwSelDocumentoVenta.class);
            DetachedCriteria detachedCriteria1 = DetachedCriteria.forClass(VwSelDocumentoVenta.class);
            DetachedCriteria detachedCriteria2 = DetachedCriteria.forClass(VwSelDocumentoVenta.class);
            DetachedCriteria detachedCriteria3 = DetachedCriteria.forClass(VwSelDocumentoVenta.class);
            DetachedCriteria detachedCriteria4 = DetachedCriteria.forClass(VwSelDocumentoVenta.class);
            DetachedCriteria detachedCriteria5 = DetachedCriteria.forClass(VwSelDocumentoVenta.class);
            DetachedCriteria detachedCriteria6 = DetachedCriteria.forClass(VwSelDocumentoVenta.class);
            DetachedCriteria detachedCriteria7 = DetachedCriteria.forClass(VwSelDocumentoVenta.class);
            for (ParametroDto parametro : parametros) {
                System.out.println("parametro.getNombre() "+parametro.getNombre());
                System.out.println("parametro.getValor() "+parametro.getValor());
                if (parametro.getNombre().equals("currentPage") && parametro.getValor() != null) {
                    currentPage = Integer.parseInt(parametro.getValor());
                } else if (parametro.getNombre().equals("itemsPerPage") && parametro.getValor() != null) {
                    itemsPerPage = Integer.parseInt(parametro.getValor());
                } else if (parametro.getNombre().equals("codigoTipo") && parametro.getValor() != null) {
                    detachedCriteria.add(Property.forName(parametro.getNombre()).eq(parametro.getValor()));
                    detachedCriteria1.add(Property.forName(parametro.getNombre()).eq(parametro.getValor()));
                    detachedCriteria2.add(Property.forName(parametro.getNombre()).eq(parametro.getValor()));
                    detachedCriteria3.add(Property.forName(parametro.getNombre()).eq(parametro.getValor()));
                    detachedCriteria4.add(Property.forName(parametro.getNombre()).eq(parametro.getValor()));
                    detachedCriteria5.add(Property.forName(parametro.getNombre()).eq(parametro.getValor()));
                } else if (parametro.getNombre().equals("serie") && parametro.getValor() != null) {
                    detachedCriteria.add(Property.forName(parametro.getNombre()).eq(parametro.getValor()));
                    detachedCriteria1.add(Property.forName(parametro.getNombre()).eq(parametro.getValor()));
                    detachedCriteria2.add(Property.forName(parametro.getNombre()).eq(parametro.getValor()));
                    detachedCriteria3.add(Property.forName(parametro.getNombre()).eq(parametro.getValor()));
                    detachedCriteria4.add(Property.forName(parametro.getNombre()).eq(parametro.getValor()));
                    detachedCriteria5.add(Property.forName(parametro.getNombre()).eq(parametro.getValor()));
                } else if (parametro.getNombre().equals("codigoEstado") && parametro.getValor() != null) {
                    detachedCriteria.add(Property.forName(parametro.getNombre()).eq(parametro.getValor()));
                    detachedCriteria1.add(Property.forName(parametro.getNombre()).eq(parametro.getValor()));
                    detachedCriteria2.add(Property.forName(parametro.getNombre()).eq(parametro.getValor()));
                    detachedCriteria3.add(Property.forName(parametro.getNombre()).eq(parametro.getValor()));
                    detachedCriteria4.add(Property.forName(parametro.getNombre()).eq(parametro.getValor()));
                    detachedCriteria5.add(Property.forName(parametro.getNombre()).eq(parametro.getValor()));
                } else if (parametro.getNombre().equals("numero") && parametro.getValor() != null) {
                    detachedCriteria.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%"));
                    detachedCriteria1.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%"));
                    detachedCriteria2.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%"));
                    detachedCriteria3.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%"));
                    detachedCriteria4.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%"));
                    detachedCriteria5.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%"));
                } else if (parametro.getNombre().equals("documentoRef") && parametro.getValor() != null) {
                    detachedCriteria.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%"));
                    detachedCriteria1.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%"));
                    detachedCriteria2.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%"));
                    detachedCriteria3.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%"));
                    detachedCriteria4.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%"));
                    detachedCriteria5.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%"));
                } else if (parametro.getNombre().equals("fechaEmision") && parametro.getValor() != null) {
                    detachedCriteria.add(Property.forName(parametro.getNombre()).eq(dateFormat.parse((parametro.getValor()))));
                    detachedCriteria1.add(Property.forName(parametro.getNombre()).eq(dateFormat.parse((parametro.getValor()))));
                    detachedCriteria2.add(Property.forName(parametro.getNombre()).eq(dateFormat.parse((parametro.getValor()))));
                    detachedCriteria3.add(Property.forName(parametro.getNombre()).eq(dateFormat.parse((parametro.getValor()))));
                    detachedCriteria4.add(Property.forName(parametro.getNombre()).eq(dateFormat.parse((parametro.getValor()))));
                    detachedCriteria5.add(Property.forName(parametro.getNombre()).eq(dateFormat.parse((parametro.getValor()))));
                } else if (parametro.getNombre().equals("documentoCliente") && parametro.getValor() != null) {
                    detachedCriteria.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%"));
                    detachedCriteria1.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%"));
                    detachedCriteria2.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%"));
                    detachedCriteria3.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%"));
                    detachedCriteria4.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%"));
                    detachedCriteria5.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%"));
                } else if (parametro.getNombre().equals("nombreCliente") && parametro.getValor() != null) {
                    detachedCriteria.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%").ignoreCase());
                    detachedCriteria1.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%").ignoreCase());
                    detachedCriteria2.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%").ignoreCase());
                    detachedCriteria3.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%").ignoreCase());
                    detachedCriteria4.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%").ignoreCase());
                    detachedCriteria5.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%").ignoreCase());
                } else if (parametro.getNombre().equals("fechaDesde") && parametro.getValor() != null) {
                    fechaDesde = dateFormat.parse(parametro.getValor());
                } else if (parametro.getNombre().equals("fechaHasta") && parametro.getValor() != null) {
                    fechaHasta = dateFormat.parse(parametro.getValor());
                }
            }
            if (fechaDesde != null && fechaHasta != null) {
                detachedCriteria.add(Restrictions.between("fechaEmision", fechaDesde, fechaHasta));
                detachedCriteria1.add(Restrictions.between("fechaEmision", fechaDesde, fechaHasta));
                detachedCriteria2.add(Restrictions.between("fechaEmision", fechaDesde, fechaHasta));
                detachedCriteria3.add(Restrictions.between("fechaEmision", fechaDesde, fechaHasta));
                detachedCriteria4.add(Restrictions.between("fechaEmision", fechaDesde, fechaHasta));
                detachedCriteria5.add(Restrictions.between("fechaEmision", fechaDesde, fechaHasta));
            }
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            lista = detachedCriteria.getExecutableCriteria(sesion).setFirstResult(currentPage).setMaxResults(itemsPerPage).addOrder(Order.desc("id")).list();
            nroDocumentoVentas = (long) detachedCriteria1.getExecutableCriteria(sesion).setProjection(Projections.rowCount()).uniqueResult();
            Object objectTotalFacturas = detachedCriteria2.getExecutableCriteria(sesion).add(Property.forName("codigoTipo").eq("01")).add(Property.forName("codigoEstado").eq("VAL")).setProjection(Projections.sum("total")).uniqueResult();
            totalFacturas = (objectTotalFacturas == null ? (double) 0 : (double) objectTotalFacturas);
            Object objectTotalBoletas = detachedCriteria3.getExecutableCriteria(sesion).add(Property.forName("codigoTipo").eq("03")).add(Property.forName("codigoEstado").eq("VAL")).setProjection(Projections.sum("total")).uniqueResult();
            totalBoletas = (objectTotalBoletas == null ? (double) 0 : (double) objectTotalBoletas);
            Object objectNotaCreditos = detachedCriteria4.getExecutableCriteria(sesion).add(Property.forName("codigoTipo").eq("07")).add(Property.forName("codigoEstado").eq("VAL")).setProjection(Projections.sum("total")).uniqueResult();
            totalNotaCreditos = (objectNotaCreditos == null ? (double) 0 : (double) objectNotaCreditos);
            Object objectNotaDebitos = detachedCriteria5.getExecutableCriteria(sesion).add(Property.forName("codigoTipo").eq("08")).add(Property.forName("codigoEstado").eq("VAL")).setProjection(Projections.sum("total")).uniqueResult();
            totalNotaDebitos = (objectNotaDebitos == null ? (double) 0 : (double) objectNotaDebitos);
            Object objectTotalPendienteEnvioSunat = detachedCriteria6.getExecutableCriteria(sesion).add(Property.forName("codigoEstado").eq("PES")).setProjection(Projections.count("id")).uniqueResult();
            totalPendienteEnvioSunat = (objectTotalPendienteEnvioSunat == null ? (long) 0 : (long) objectTotalPendienteEnvioSunat);
            Object objectTotalPendienteComunicacionBaja = detachedCriteria7.getExecutableCriteria(sesion).add(Property.forName("codigoEstado").eq("PCB")).setProjection(Projections.count("id")).uniqueResult();
            totalPendienteComunicacionBajas = (objectTotalPendienteComunicacionBaja == null ? (long) 0 : (long) objectTotalPendienteComunicacionBaja);
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        listaDocumentoVentaDto.setDocumentoVentas(lista);
        listaDocumentoVentaDto.setNroDocumentoVentas(nroDocumentoVentas);
        listaDocumentoVentaDto.setTotalFacturas(totalFacturas);
        listaDocumentoVentaDto.setTotalBoletas(totalBoletas);
        listaDocumentoVentaDto.setTotalNotaCreditos(totalNotaCreditos);
        listaDocumentoVentaDto.setTotalNotaDebitos(totalNotaDebitos);
        listaDocumentoVentaDto.setTotalPendienteEnvioSunats(totalPendienteEnvioSunat);
        listaDocumentoVentaDto.setTotalPendienteComunicacionBajas(totalPendienteComunicacionBajas);

        return listaDocumentoVentaDto;
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
            DocumentoVenta documentoVenta = ((notificacionDto.getDocumentoVenta() == null) ? buscar(notificacionDto.getIdDocumentoVenta()) : notificacionDto.getDocumentoVenta());
            String nombreDocumento = documentoVenta.getEmpresa().getEntidad().getDocumento() + "-" + documentoVenta.getTipoDocumentoVenta().getCodigo() + "-" + documentoVenta.getPuntoVentaSerie().getCodigo() + "-" + documentoVenta.getNumero();

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
                if (documentoVenta.getCliente().getEntidad().getCorreoElectronico1() != null && !documentoVenta.getCliente().getEntidad().getCorreoElectronico1().trim().equals("")) {
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(documentoVenta.getCliente().getEntidad().getCorreoElectronico1()));
                }
                if (documentoVenta.getCliente().getEntidad().getCorreoElectronico2() != null && !documentoVenta.getCliente().getEntidad().getCorreoElectronico2().trim().equals("")) {
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(documentoVenta.getCliente().getEntidad().getCorreoElectronico2()));
                }
                if (documentoVenta.getCliente().getEntidad().getCorreoElectronico3() != null && !documentoVenta.getCliente().getEntidad().getCorreoElectronico3().trim().equals("")) {
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(documentoVenta.getCliente().getEntidad().getCorreoElectronico3()));
                }
            } else {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(notificacionDto.getEmail()));
            }
            message.setSubject(asunto);
            BodyPart messageBodyPart = new MimeBodyPart();
            String mensaje = saludo + "<br><br>\n"
                    + "<div style='width: 600px;background:#3F51B5;color: #ffffff;margin: 0 auto;text-align: center;padding: 5px;border-radius: 15px;'>\n"
                    + "            <img style='width: 160px;height: 204px;' src='http://gepp.pe/assets/images/gepp_transmap.png'><h1>" + nombreDocumento + "</h1>\n"
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
                    + "Saludos,<br>\n"
                    + documentoVenta.getEmpresa().getEntidad().getNombre();
            messageBodyPart.setContent(mensaje, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            if (documentoVenta.getTipoDocumentoVenta().getCodigo().equals("01")) {
                messageBodyPart = new MimeBodyPart();
                String filename = pe.modelo.Configuracion.rutaFacturas + nombreDocumento + ".xml";
                DataSource source = new FileDataSource(filename);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(nombreDocumento + ".xml");
                multipart.addBodyPart(messageBodyPart);
            } else if (documentoVenta.getTipoDocumentoVenta().getCodigo().equals("07")) {
                messageBodyPart = new MimeBodyPart();
                String filename = pe.modelo.Configuracion.rutaNotasDeCredito + nombreDocumento + ".xml";
                DataSource source = new FileDataSource(filename);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(nombreDocumento + ".xml");
                multipart.addBodyPart(messageBodyPart);
            }
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

    @Override
    public void enviarSunat(DocumentoVenta documentoVenta) {
        try {
            if (documentoVenta.getTipoDocumentoVenta().getCodigo().equals("01")) {
                Factura factura = new Factura();
                factura.generarCaso(documentoVenta);
            } else if (documentoVenta.getTipoDocumentoVenta().getCodigo().equals("07")) {
                NotaCredito notaCredito = new NotaCredito();
                notaCredito.generarCaso(documentoVenta);
            }
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.update(documentoVenta);
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void anular(AnulacionDto anulacionDto) {
        try {
            DocumentoVenta documentoVenta = buscar(anulacionDto.getIdDocumentoVenta());
            ResumenVentas resumenVentas = new ResumenVentas();
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            if (anulacionDto.getTipo().equals("COB")) {
                EstadoDocumentoVenta estadoDocumentoVenta = new EstadoDocumentoVenta();
                estadoDocumentoVenta.setCodigo("PES");
                resumenVentas.setEmpresa(documentoVenta.getEmpresa());
                resumenVentas.setEstadoDocumentoVenta(estadoDocumentoVenta);
                resumenVentas.setFechaCreacion(new Date());
                resumenVentas.setFechaEmision(anulacionDto.getFechaEmisionDocumentoVenta());
                resumenVentas.setUsuarioByIdUsuarioCreacion(documentoVenta.getUsuarioByIdUsuarioCreacion());
                resumenVentas.setTipo("RA");
                sesion.save(resumenVentas);

                ResumenVentasGrupo resumenVentasGrupo = new ResumenVentasGrupo();
                resumenVentasGrupo.setTipoDocumentoVenta(documentoVenta.getTipoDocumentoVenta());
                resumenVentasGrupo.setPuntoVentaSerie(documentoVenta.getPuntoVentaSerie());
                resumenVentasGrupo.setMoneda(documentoVenta.getMoneda());
                resumenVentasGrupo.setInicioDocumentoVenta(documentoVenta.getNumero());
                resumenVentasGrupo.setFinDocumentoVenta(documentoVenta.getNumero());
                resumenVentasGrupo.setFechaCreacion(new Date());
                resumenVentasGrupo.setDescripcion(anulacionDto.getMotivo());
                resumenVentasGrupo.setResumenVentas(resumenVentas);
                resumenVentasGrupo.setUsuarioByIdUsuarioCreacion(documentoVenta.getUsuarioByIdUsuarioCreacion());
                sesion.save(resumenVentasGrupo);

                ResumenVentasGrupoVenta resumenVentasGrupoVenta = new ResumenVentasGrupoVenta();
                resumenVentasGrupoVenta.setDocumentoVenta(documentoVenta);
                resumenVentasGrupoVenta.setResumenVentasGrupo(resumenVentasGrupo);
                resumenVentasGrupoVenta.setFechaCreacion(new Date());
                resumenVentasGrupoVenta.setUsuarioByIdUsuarioCreacion(documentoVenta.getUsuarioByIdUsuarioCreacion());
                sesion.save(resumenVentasGrupoVenta);
                String hqlUpdate = "update DocumentoVenta set codigo_estado_documento_venta = :codigo_estado where id = :id";
                sesion.createQuery(hqlUpdate)
                        .setString("codigo_estado", "PCB")
                        .setLong("id", anulacionDto.getIdDocumentoVenta())
                        .executeUpdate();
                
            } else {
                String hqlUpdate = "update DocumentoVenta set codigo_estado_documento_venta = :codigo_estado where id = :id";
                sesion.createQuery(hqlUpdate)
                        .setString("codigo_estado", "ANU")
                        .setLong("id", anulacionDto.getIdDocumentoVenta())
                        .executeUpdate();
            }
            sesion.getTransaction().commit();
            sesion.close();
            if (anulacionDto.getTipo().equals("COB")) {
                ResumenVentaDao resumenVentaDao = new ResumenVentaDao();
                resumenVentas = resumenVentaDao.buscar(resumenVentas.getId());
                resumenVentaDao.enviarComunicacionBajaSunat(resumenVentas);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
