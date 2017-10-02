/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.ventas;

import java.util.Date;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import pe.modelo.dao.HibernateUtil;
import pe.modelo.pojo.PuntoVenta;

/**
 *
 * @author octavio
 */
public class PuntoVentaDao implements IPuntoVentaDao {

    @Override
    public void crear(PuntoVenta puntoVenta) {
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.save(puntoVenta);
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editar(PuntoVenta puntoVenta) {
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            String hqlUpdate = "update PuntoVenta set nombre = :nombre,descripcion = :descripcion,admin = :admin where id = :id";
            /*sesion.createQuery(hqlUpdate)
                    .setString("nombre", puntoVenta.getNombre())
                    .setString("descripcion", puntoVenta.getDescripcion())
                    .setBoolean("admin", puntoVenta.isAdmin())
                    .setLong("id", puntoVenta.getId())
                    .executeUpdate();*/
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public PuntoVenta buscar(long id) {
        PuntoVenta puntoVenta = null;
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            puntoVenta = (PuntoVenta) sesion.load(PuntoVenta.class, id);
            Hibernate.initialize(puntoVenta);
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return puntoVenta;
    }

    @Override
    public void eliminar(long id) {
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            String hql = "delete from PuntoVenta where id= :id";
            sesion.createQuery(hql).setLong("id", id).executeUpdate();
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PuntoVenta> listar() {
        List<PuntoVenta> lista = null;
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            Query query = sesion.createQuery("from PuntoVenta order by id");
            lista = query.list();
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
