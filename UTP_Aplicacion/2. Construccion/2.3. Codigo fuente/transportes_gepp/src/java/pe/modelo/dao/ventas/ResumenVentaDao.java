/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.ventas;

import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pe.modelo.dao.HibernateUtil;
import pe.modelo.dto.ventas.facturacion.Factura;
import pe.modelo.dto.ventas.facturacion.ResumenBoletas;
import pe.modelo.pojo.ResumenVentas;
import pe.modelo.pojo.ResumenVentasGrupo;
import pe.modelo.pojo.ResumenVentasGrupoVenta;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import net.sf.jasperreports.engine.util.JRLoader;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import pe.modelo.dao.HibernateUtil;
import pe.modelo.dto.ParametroDto;
import pe.modelo.dto.ventas.ListaResumenesVentasDto;
import pe.modelo.dto.ventas.facturacion.ComunicacionBaja;
import pe.modelo.pojo.DocumentoVenta;
import pe.modelo.pojo.EstadoDocumentoVenta;
import pe.modelo.pojo.Producto;
import pe.modelo.pojo.ResumenVentas;
import pe.modelo.pojo.ResumenVentasGrupo;
import pe.modelo.pojo.ResumenVentasGrupoVenta;
import pe.modelo.pojo.vista.VwSelResumenVentas;

/**
 *
 * @author HP
 */
public class ResumenVentaDao implements IResumenVentaDao {

    @Override
    public void crear(ResumenVentas resumenVentas) {
        try {
            System.out.println("resumenVentasGrupoVenta " + resumenVentas);
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.save(resumenVentas);
            for (ResumenVentasGrupo resumenVentasGrupo : resumenVentas.getResumenVentasGrupos()) {
                resumenVentasGrupo.setResumenVentas(resumenVentas);
                //resumenVentasGrupo.setFechaCreacion(new Date());
                sesion.save(resumenVentasGrupo);
                for (ResumenVentasGrupoVenta resumenVentasGrupoVenta : resumenVentasGrupo.getResumenVentasGrupoVentas()) {
                    resumenVentasGrupoVenta.setResumenVentasGrupo(resumenVentasGrupo);
                    //resumenVentasGrupo.setFechaCreacion(new Date());

                    sesion.save(resumenVentasGrupoVenta);

                    /*DocumentoVenta documentoVenta = resumenVentasGrupoVenta.getDocumentoVenta();
                     System.out.println(documentoVenta.getId());
                     System.out.println(documentoVenta.getEstadoDocumentoVenta().getCodigo());
                     sesion.update(documentoVenta);*/
                }
            }
            sesion.getTransaction().commit();
            sesion.close();
            resumenVentas = buscar(resumenVentas.getId());
            System.out.println("ID-- " + resumenVentas.getId());
            //enviarSunat(resumenVentas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@Override
    public void enviarSunat(ResumenVentas resumenVentas) {
        try {
            ResumenBoletas resumenBoletas = new ResumenBoletas();
            resumenBoletas.generarCaso(resumenVentas);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*@Override
     public void editar(Producto producto) {
     try {
     Session sesion = HibernateUtil.getSessionFactory().openSession();
     sesion.beginTransaction();
     String hqlUpdate = "update Producto set nombre = :nombre,descripcion = :descripcion,unidad = :unidad,precioCompra = :precioCompra,precioVenta = :precioVenta where id = :id";
     sesion.createQuery(hqlUpdate)
     .setString("nombre", producto.getNombre())
     .setString("descripcion", producto.getDescripcion())
     .setString("unidad", producto.getUnidad().getCodigo())
     .setDouble("precioCompra", producto.getPrecioCompra())
     .setDouble("precioVenta", producto.getPrecioVenta())
     .setLong("id", producto.getId())
     .executeUpdate();
     sesion.getTransaction().commit();
     sesion.close();
     } catch (Exception e) {
     e.printStackTrace();
     }
     }*/
    /* @Override
     public void eliminar(long id) {
     try {
     Session sesion = HibernateUtil.getSessionFactory().openSession();
     sesion.beginTransaction();
     String hql = "delete from Producto where id= :id";
     sesion.createQuery(hql).setLong("id", id).executeUpdate();
     sesion.getTransaction().commit();
     sesion.close();
     } catch (Exception e) {
     e.printStackTrace();
     }
     }*/
    @Override
    public ListaResumenesVentasDto listar(ParametroDto[] parametros) {
        ListaResumenesVentasDto listaResumenesVentasDto = new ListaResumenesVentasDto();
        List<VwSelResumenVentas> lista = null;
        long NroResumenesVentas = 0;
        Integer currentPage = 0;
        Integer itemsPerPage = 0;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        try {
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(VwSelResumenVentas.class);
            DetachedCriteria detachedCriteria1 = DetachedCriteria.forClass(VwSelResumenVentas.class);
            for (ParametroDto parametro : parametros) {
                if (parametro.getNombre().equals("currentPage") && parametro.getValor() != null) {
                    currentPage = Integer.parseInt(parametro.getValor());
                } else if (parametro.getNombre().equals("itemsPerPage") && parametro.getValor() != null) {
                    itemsPerPage = Integer.parseInt(parametro.getValor());
                } else if (parametro.getNombre().equals("numero") && parametro.getValor() != null) {
                    detachedCriteria.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%"));
                } else if (parametro.getNombre().equals("fechaEmision") && parametro.getValor() != null) {
                    detachedCriteria.add(Property.forName(parametro.getNombre()).eq(dateFormat.parse((parametro.getValor()))));
                } else if (parametro.getNombre().equals("codigoEstado") && parametro.getValor() != null) {
                    detachedCriteria.add(Property.forName(parametro.getNombre()).eq(parametro.getValor()));
                }
            }
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            lista = detachedCriteria.getExecutableCriteria(sesion).setFirstResult(currentPage).setMaxResults(itemsPerPage).addOrder(Order.desc("id")).list();
            NroResumenesVentas = (long) detachedCriteria1.getExecutableCriteria(sesion).setProjection(Projections.rowCount()).uniqueResult();
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        listaResumenesVentasDto.setVwSelResumenesVentas(lista);
        listaResumenesVentasDto.setNroResumenesVentas(NroResumenesVentas);
        System.out.println("listaResumenesVentasDto " + listaResumenesVentasDto);
        return listaResumenesVentasDto;
    }

    @Override
    public void enviarComunicacionBajaSunat(ResumenVentas resumenVentas) {
        try {
            ComunicacionBaja comunicacionBaja = new ComunicacionBaja();
            comunicacionBaja.generarCaso(resumenVentas);
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.update(resumenVentas);
            for (ResumenVentasGrupo resumenVentasGrupo : resumenVentas.getResumenVentasGrupos()) {
                for (ResumenVentasGrupoVenta resumenVentasGrupoVenta : resumenVentasGrupo.getResumenVentasGrupoVentas()) {
                    DocumentoVenta documentoVenta = resumenVentasGrupoVenta.getDocumentoVenta();
                    if (resumenVentas.getEstadoDocumentoVenta().getCodigo().equals("VAL")) {
                        EstadoDocumentoVenta estadoDocumentoVenta = new EstadoDocumentoVenta();
                        estadoDocumentoVenta.setCodigo("ANU");
                        documentoVenta.setEstadoDocumentoVenta(estadoDocumentoVenta);
                        sesion.update(documentoVenta);
                    } else if (resumenVentas.getEstadoDocumentoVenta().getCodigo().equals("REC")) {
                        EstadoDocumentoVenta estadoDocumentoVenta = new EstadoDocumentoVenta();
                        estadoDocumentoVenta.setCodigo("VAL");
                        documentoVenta.setEstadoDocumentoVenta(estadoDocumentoVenta);
                        sesion.update(documentoVenta);
                    } else if (resumenVentas.getEstadoDocumentoVenta().getCodigo().equals("PES")) {
                        EstadoDocumentoVenta estadoDocumentoVenta = new EstadoDocumentoVenta();
                        estadoDocumentoVenta.setCodigo("PCB");
                        documentoVenta.setEstadoDocumentoVenta(estadoDocumentoVenta);
                        sesion.update(documentoVenta);
                    }
                }
            }
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void enviarResumenBoletasSunat(ResumenVentas resumenVentas) {
        try {
            ResumenBoletas resumenBoletas = new ResumenBoletas();
            resumenBoletas.generarCaso(resumenVentas);
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.update(resumenVentas);
            for (ResumenVentasGrupo resumenVentasGrupo : resumenVentas.getResumenVentasGrupos()) {
                for (ResumenVentasGrupoVenta resumenVentasGrupoVenta : resumenVentasGrupo.getResumenVentasGrupoVentas()) {
                    DocumentoVenta documentoVenta = resumenVentasGrupoVenta.getDocumentoVenta();
                    if (resumenVentas.getEstadoDocumentoVenta().getCodigo().equals("REC")) {
                        EstadoDocumentoVenta estadoDocumentoVenta = new EstadoDocumentoVenta();
                        estadoDocumentoVenta.setCodigo("PES");
                        documentoVenta.setEstadoDocumentoVenta(estadoDocumentoVenta);
                        sesion.update(documentoVenta);
                    } else if (resumenVentas.getEstadoDocumentoVenta().getCodigo().equals("VAL")) {
                        EstadoDocumentoVenta estadoDocumentoVenta = new EstadoDocumentoVenta();
                        estadoDocumentoVenta.setCodigo("VAL");
                        documentoVenta.setEstadoDocumentoVenta(estadoDocumentoVenta);
                        sesion.update(documentoVenta);
                    }
                }
            }
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void consultarEstadoProceso(ResumenVentas resumenVentas) {
        try {
            ComunicacionBaja comunicacionBaja = new ComunicacionBaja();
            comunicacionBaja.consultarEstadoProceso(resumenVentas);
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.update(resumenVentas);
            for (ResumenVentasGrupo resumenVentasGrupo : resumenVentas.getResumenVentasGrupos()) {
                for (ResumenVentasGrupoVenta resumenVentasGrupoVenta : resumenVentasGrupo.getResumenVentasGrupoVentas()) {
                    DocumentoVenta documentoVenta = resumenVentasGrupoVenta.getDocumentoVenta();
                    if (resumenVentas.getEstadoDocumentoVenta().getCodigo().equals("VAL")) {
                        EstadoDocumentoVenta estadoDocumentoVenta = new EstadoDocumentoVenta();
                        estadoDocumentoVenta.setCodigo("ANU");
                        documentoVenta.setEstadoDocumentoVenta(estadoDocumentoVenta);
                        sesion.update(documentoVenta);
                    } else if (resumenVentas.getEstadoDocumentoVenta().getCodigo().equals("REC")) {
                        EstadoDocumentoVenta estadoDocumentoVenta = new EstadoDocumentoVenta();
                        estadoDocumentoVenta.setCodigo("VAL");
                        documentoVenta.setEstadoDocumentoVenta(estadoDocumentoVenta);
                        sesion.update(documentoVenta);
                    } else if (resumenVentas.getEstadoDocumentoVenta().getCodigo().equals("PES")) {
                        EstadoDocumentoVenta estadoDocumentoVenta = new EstadoDocumentoVenta();
                        estadoDocumentoVenta.setCodigo("PCB");
                        documentoVenta.setEstadoDocumentoVenta(estadoDocumentoVenta);
                        sesion.update(documentoVenta);
                    }
                }
            }
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResumenVentas buscar(long id) {
        ResumenVentas resumenVentas = null;
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            resumenVentas = (ResumenVentas) sesion.load(ResumenVentas.class, id);
            Hibernate.initialize(resumenVentas);
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resumenVentas;
    }

    @Override
    public JasperPrint generarReporte(ResumenVentas resumenVentas) {
        JasperPrint rptResumenVentas = null;
        try {
            JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("../../reporte/ventas/rptResumenDeBoletas.jasper"));
            Map parameters = new HashMap();
            Locale locale = new Locale("es", "PE");
            parameters.put(JRParameter.REPORT_LOCALE, locale);
            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(resumenVentas.getResumenVentasGrupos());
            rptResumenVentas = JasperFillManager.fillReport(report, parameters, ds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rptResumenVentas;
    }

    @Override
    public void anular(long id) {
        try {
            System.out.println("anular "+id);
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            String hqlUpdate = "update ResumenVentas set codigo_estado_documento_venta = :codigo_estado where id = :id";
            sesion.createQuery(hqlUpdate)
                    .setString("codigo_estado", "ANU")
                    .setLong("id", id)
                    .executeUpdate();
            sesion.getTransaction().commit();
            sesion.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
