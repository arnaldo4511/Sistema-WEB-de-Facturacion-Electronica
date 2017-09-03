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
import pe.modelo.pojo.Producto;
import pe.modelo.pojo.ResumenVentas;
import pe.modelo.pojo.ResumenVentasGrupo;
import pe.modelo.pojo.ResumenVentasGrupoVenta;

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
            }
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
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
    }
    
    @Override
    public Producto buscar(long id) {
        Producto producto = null;
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            producto = (Producto) sesion.load(Producto.class, id);
            Hibernate.initialize(producto);
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return producto;
    }

    @Override
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
    public List<ResumenVentas> listar() {
        List<ResumenVentas> lista = null;
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            Query query = sesion.createQuery("from ResumenVentas order by id");
            lista = query.list();
            sesion.getTransaction().commit();
            sesion.close();
            System.out.println("query " + query);
            System.out.println("listar " + lista);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }


}
