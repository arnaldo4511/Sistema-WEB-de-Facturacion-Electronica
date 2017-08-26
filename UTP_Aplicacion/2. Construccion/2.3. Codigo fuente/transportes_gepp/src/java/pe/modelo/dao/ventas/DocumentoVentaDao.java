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
import net.sf.jasperreports.engine.JRParameter;
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
import pe.modelo.dto.ventas.Parametro;
import pe.modelo.dao.HibernateUtil;
import pe.modelo.pojo.DocumentoVenta;
import pe.modelo.pojo.DocumentoVentaDetalle;
import pe.modelo.pojo.TipoDocumentoVenta;

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
    public JasperPrint generarReporte(long id) {
        DocumentoVenta documentoVenta = null;
        JasperPrint rptDocumentoVenta = null;
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            documentoVenta = (DocumentoVenta) sesion.load(DocumentoVenta.class, id);
            //Hibernate.initialize(documentoVenta);
            JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("../../dto/ventas/rptDocumentoVenta.jasper"));
            Map parameters = new HashMap();
            //parameters.put("id", 1);
            Locale locale = new Locale("es", "PE");
            parameters.put(JRParameter.REPORT_LOCALE, locale);
            //parameters.put(JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION, sesion);

            //parameters.put(JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION, session);
            //parameters.put("id", id);
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(documentoVenta.getDocumentoVentaDetalles());
            rptDocumentoVenta = JasperFillManager.fillReport(report, parameters, ds);
            //List usuarios = session.createQuery("from UsuarioBean").list();
//fileName = reporte.Path(REPORTE_IMPORTE);

//cargamos el archivo
//disenioReporte = JRXmlLoader.load(fileName); 
//lo compilamos
//JasperReport report = JasperCompileManager.compileReport(disenioReporte) ;
//obtenmos de forma adecuada los valores de las select para incorporalos.
//HibernateQueryResultDataSource ds = new HibernateQueryResultDataSource(usuarios,campos);
//obtenemos lo que vamos a sacar
//bytes = JasperRunManager.runReportToPdf(report, parameters, ds);
            sesion.getTransaction().commit();
            sesion.close();
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
    public List<DocumentoVenta> listar(Parametro[] parametros) {
        List<DocumentoVenta> lista = null;
        try {
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DocumentoVenta.class);
            if (!parametros[0].getValor().equals("")) {
                detachedCriteria.add(Property.forName("tipoDocumentoVenta.codigo").eq(parametros[0].getValor()));
            }
            detachedCriteria.add(Property.forName("numero").like("%" + parametros[3].getValor() + "%"));
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            lista = detachedCriteria.getExecutableCriteria(sesion).setFirstResult(Integer.parseInt(parametros[1].getValor())).setMaxResults(Integer.parseInt(parametros[2].getValor())).list();
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
    public long totalDocumentoVentas(Parametro[] parametros) {
        long total = 0;
        try {
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DocumentoVenta.class);
            if (!parametros[0].getValor().equals("")) {
                detachedCriteria.add(Property.forName("tipoDocumentoVenta.codigo").eq(parametros[0].getValor()));
            }
            detachedCriteria.add(Property.forName("numero").like("%" + parametros[3].getValor() + "%"));

            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            total = (detachedCriteria.getExecutableCriteria(sesion).list().size());
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
}
