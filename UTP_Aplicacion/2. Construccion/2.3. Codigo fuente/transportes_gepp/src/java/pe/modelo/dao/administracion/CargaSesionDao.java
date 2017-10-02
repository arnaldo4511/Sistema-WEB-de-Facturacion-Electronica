/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.administracion;

import java.util.Iterator;
import pe.modelo.dto.CargaSesionDto;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.DoubleType;
import org.hibernate.type.StringType;
import pe.modelo.dao.HibernateUtil;
import pe.modelo.pojo.*;
import pe.modelo.pojo.vista.VwRptVentaXPlacaAnual;

/**
 *
 * @author octavio
 */
public class CargaSesionDao implements ICargaSesionDao {

    @Override
    public CargaSesionDto crear(long idUsuario) {
        CargaSesionDto cargaSesion = new CargaSesionDto();
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            Usuario usuario = (Usuario) sesion.load(Usuario.class, idUsuario);
            Hibernate.initialize(usuario);
            Query query = sesion.createQuery("from Rol order by id");
            List<Rol> roles = query.list();
            Query query2 = sesion.createQuery("from PuntoVenta order by id");
            List<PuntoVenta> puntoVentas = query2.list();
            Query query3 = sesion.createQuery("from Unidad order by codigo");
            List<Unidad> unidades = query3.list();
            Query query4 = sesion.createQuery("from TipoDocumentoEntidad order by codigo");
            List<TipoDocumentoEntidad> tipoDocumentoEntidads = query4.list();
            Query query5 = sesion.createQuery("from TipoDocumentoVenta order by codigo");
            List<TipoDocumentoVenta> tipoDocumentoVentas = query5.list();
            Query query6 = sesion.createQuery("from TipoNotaCredito order by codigo");
            List<TipoNotaCredito> tipoNotaCreditos = query6.list();
            Query query7 = sesion.createQuery("from TipoNotaDebito order by codigo");
            List<TipoNotaDebito> tipoNotaDebitos = query7.list();
            Query query8 = sesion.createQuery("from EstadoDocumentoVenta order by codigo");
            List<EstadoDocumentoVenta> estadoDocumentoVentas = query8.list();
            Query query9 = sesion.createQuery("from PuntoVentaSerie order by codigo");
            List<PuntoVentaSerie> puntoVentaSeries = query9.list();
            Query query10 = sesion.createQuery("from Producto order by id");
            List<Producto> productos = query10.list();
            Query query11 = sesion.createQuery("from Vehiculo where activo=true order by serie,placa");
            List<Vehiculo> vehiculos = query11.list();
            //Query query12 = sesion.createSQLQuery("SELECT * FROM ventas.fn_acu_documento_venta(2017, 1)");

            //Iterator iterator = query12.list().iterator();

            /*while (iterator.hasNext()) {
                Object[] row = (Object[]) iterator.next();
                System.out.println("inicio");
                System.out.println(row[0]);
                System.out.println(row);
                System.out.println("fin");
                // row[0] - contains first column value (you have to cast it to appropriate class)
                // row[1] - contains second column value
                // etc.
            }*/
 /*for (FnAcuDocumentoVenta fnAcuDocumentoVenta : fnAcuDocumentoVentas) {
                System.out.println("getPlaca:" + fnAcuDocumentoVenta.getPlaca());
            }*/
            sesion.getTransaction().commit();
            sesion.close();
            cargaSesion.setUsuario(usuario);
            cargaSesion.setRoles(roles);
            cargaSesion.setPuntoVentas(puntoVentas);
            cargaSesion.setUnidades(unidades);
            cargaSesion.setTipoDocumentoEntidads(tipoDocumentoEntidads);
            cargaSesion.setTipoDocumentoVentas(tipoDocumentoVentas);
            cargaSesion.setTipoNotaCreditos(tipoNotaCreditos);
            cargaSesion.setTipoNotaDebitos(tipoNotaDebitos);
            cargaSesion.setEstadoDocumentoVentas(estadoDocumentoVentas);
            cargaSesion.setPuntoVentaSeries(puntoVentaSeries);
            cargaSesion.setProductos(productos);
            cargaSesion.setVehiculos(vehiculos);
            //cargaSesion.setVwRptVentaXPlacaAnuals(vwRptVentaXPlacaAnuals);

            //cargaSesion.setVwSelDocumentoVentas(vwSelDocumentoVentas);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cargaSesion;
    }

}
