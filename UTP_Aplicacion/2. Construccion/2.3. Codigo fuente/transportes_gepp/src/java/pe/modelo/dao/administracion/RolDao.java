/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.administracion;

import java.util.Date;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import pe.modelo.dao.HibernateUtil;
import pe.modelo.dto.ParametroDto;
import pe.modelo.pojo.Rol;
import pe.modelo.pojo.RolMenu;
import pe.modelo.pojo.vista.VwSelRol;

/**
 *
 * @author octavio
 */
public class RolDao implements IRolDao {

    @Override
    public void crear(Rol rol) {
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.save(rol);
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editar(Rol rol) {
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            String hqlUpdate = "update Rol set nombre = :nombre,descripcion = :descripcion,admin = :admin where id = :id";
            sesion.createQuery(hqlUpdate)
                    .setString("nombre", rol.getNombre())
                    .setString("descripcion", rol.getDescripcion())
                    .setBoolean("admin", rol.isAdmin())
                    .setLong("id", rol.getId())
                    .executeUpdate();
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Rol buscar(long id) {
        Rol rol = null;
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            rol = (Rol) sesion.load(Rol.class, id);
            Hibernate.initialize(rol);
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rol;
    }

    @Override
    public void eliminar(long id) {
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            String hql = "delete from Rol where id= :id";
            sesion.createQuery(hql).setLong("id", id).executeUpdate();
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ListaRol listar(ParametroDto[] parametros) {
        ListaRol listaRol = new ListaRol();
        Long nroRoles = new Long(0);
        List<VwSelRol> roles = null;
        Integer currentPage = 0;
        Integer itemsPerPage = 0;
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(VwSelRol.class);
        DetachedCriteria detachedCriteria1 = DetachedCriteria.forClass(VwSelRol.class);
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            for (ParametroDto parametro : parametros) {
                if (parametro.getNombre().equals("currentPage") && parametro.getValor() != null) {
                    currentPage = Integer.parseInt(parametro.getValor());
                } else if (parametro.getNombre().equals("itemsPerPage") && parametro.getValor() != null) {
                    itemsPerPage = Integer.parseInt(parametro.getValor());
                } else if (parametro.getNombre().equals("nombre") && parametro.getValor() != null) {
                    detachedCriteria.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%"));
                    detachedCriteria1.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%"));
                } else if (parametro.getNombre().equals("descripcion") && parametro.getValor() != null) {
                    detachedCriteria.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%"));
                    detachedCriteria1.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%"));
                } else if (parametro.getNombre().equals("admin") && parametro.getValor() != null) {
                    detachedCriteria.add(Property.forName(parametro.getNombre()).eq(Boolean.parseBoolean(parametro.getValor())));
                    detachedCriteria1.add(Property.forName(parametro.getNombre()).eq(Boolean.parseBoolean(parametro.getValor())));
                }
            }
            roles = detachedCriteria.getExecutableCriteria(sesion).setFirstResult(currentPage).setMaxResults(itemsPerPage).addOrder(Order.desc("id")).list();
            nroRoles = (long) detachedCriteria1.getExecutableCriteria(sesion).setProjection(Projections.rowCount()).uniqueResult();
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        listaRol.setRoles(roles);
        listaRol.setNroRoles(nroRoles);
        return listaRol;
    }

    @Override
    public List<RolMenu> listarRolMenu() {
        List<RolMenu> lista = null;
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            Query query = sesion.createQuery("from RolMenu order by id");
            lista = query.list();
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
