/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.almacen;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import pe.modelo.dao.HibernateUtil;
import pe.modelo.dto.ParametroDto;
import pe.modelo.dto.ventas.ListaProductosDto;
import pe.modelo.pojo.Producto;
import pe.modelo.pojo.vista.VwSelProductos;

/**
 *
 * @author octavio
 */
public class ProductoDao implements IProductoDao {

    @Override
    public void crear(Producto producto) {
        try {
            System.out.println("ProductoCrear " + producto);
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.save(producto);
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
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
    }

    @Override
    public ListaProductosDto listar(ParametroDto[] parametros) {
        ListaProductosDto listaProductosDto = new ListaProductosDto();
        List<VwSelProductos> lista = null;
        long nroProductos = 0;
        Integer currentPage = 0;
        Integer itemsPerPage = 0;
        
        try {
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(VwSelProductos.class);
            DetachedCriteria detachedCriteria1 = DetachedCriteria.forClass(VwSelProductos.class);
            
            for (ParametroDto parametro : parametros) {
                System.out.println("parametro.getNombre() "+parametro.getNombre());
                System.out.println("parametro.getValor() "+parametro.getValor());
                if (parametro.getNombre().equals("currentPage") && parametro.getValor() != null) {
                    currentPage = Integer.parseInt(parametro.getValor());
                } else if (parametro.getNombre().equals("itemsPerPage") && parametro.getValor() != null) {
                    itemsPerPage = Integer.parseInt(parametro.getValor());
                } else if (parametro.getNombre().equals("nombre") && parametro.getValor() != null) {
                    detachedCriteria.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%").ignoreCase());
                    detachedCriteria1.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%").ignoreCase());
                } else if (parametro.getNombre().equals("descripcion") && parametro.getValor() != null) {
                    detachedCriteria.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%"));
                    detachedCriteria1.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%"));
                }
            }
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            lista = detachedCriteria.getExecutableCriteria(sesion).setFirstResult(currentPage).setMaxResults(itemsPerPage).addOrder(Order.desc("id")).list();
            System.out.println("lista "+lista);
            nroProductos = (long) detachedCriteria1.getExecutableCriteria(sesion).setProjection(Projections.rowCount()).uniqueResult();
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        listaProductosDto.setProductos(lista);
        listaProductosDto.setNroProductos(nroProductos);
        
        return listaProductosDto;
    }

    @Override
    public List<Producto> autocompletar(String criterio) {
        List<Producto> lista = null;
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            Query query = sesion.createQuery("FROM Producto WHERE UPPER(nombre) LIKE UPPER(:criterio) order by id").setString("criterio", "%" + criterio + "%");
            lista = query.list();
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

}
