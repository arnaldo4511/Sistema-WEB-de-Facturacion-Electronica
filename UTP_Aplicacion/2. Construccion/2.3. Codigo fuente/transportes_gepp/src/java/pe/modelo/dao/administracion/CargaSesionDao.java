/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.administracion;

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
    public CargaSesion crear(long idUsuario) {
        CargaSesion cargaSesion = new CargaSesion();
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            Usuario usuario = (Usuario) sesion.load(Usuario.class, idUsuario);
            Hibernate.initialize(usuario);
            Query query = sesion.createQuery("from Rol order by id");
            List<Rol> roles = query.list();
            Query query2 = sesion.createQuery("from PuntoVenta order by id");
            List<PuntoVenta> puntoVentas = query2.list();
            Query query3 = sesion.createQuery("from Unidad");
            List<Unidad> unidades = query3.list();
            Query query4 = sesion.createQuery("from TipoDocumentoEntidad");
            List<TipoDocumentoEntidad> tiposDocumentosEntidades = query4.list();
            sesion.getTransaction().commit();
            sesion.close();
            cargaSesion.setUsuario(usuario);
            cargaSesion.setRoles(roles);
            cargaSesion.setPuntoVentas(puntoVentas);
            cargaSesion.setUnidades(unidades);
            cargaSesion.setTiposDocumentosEntidades(tiposDocumentosEntidades);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cargaSesion;
    }

}
