/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.administracion;

import pe.modelo.dto.CargaSesionDto;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import pe.modelo.dao.HibernateUtil;
import pe.modelo.pojo.*;

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

            //cargaSesion.setVwSelDocumentoVentas(vwSelDocumentoVentas);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cargaSesion;
    }

}
