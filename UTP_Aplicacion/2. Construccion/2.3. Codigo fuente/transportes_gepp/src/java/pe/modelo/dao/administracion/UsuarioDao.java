/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.administracion;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
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
import pe.modelo.pojo.Usuario;
import pe.modelo.pojo.vista.VwSelUsuario;

/**
 *
 * @author octavio
 */
public class UsuarioDao implements IUsuarioDao {

    @Override
    public void crear(Usuario usuario) {
        try {
            usuario.setFechaCreacion(new Date());
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.save(usuario);
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editar(Usuario usuario) {
        try {
            usuario.setFechaModificacion(new Date());
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.update(usuario);
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Usuario buscar(long id) {
        Usuario usuario = null;
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            usuario = (Usuario) sesion.load(Usuario.class, id);
            Hibernate.initialize(usuario);
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public void eliminar(long id) {
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            String hql = "delete from Usuario where id= :id";
            sesion.createQuery(hql).setLong("id", id).executeUpdate();
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ListaUsuario listar(ParametroDto[] parametros) {
        ListaUsuario listaUsuario = new ListaUsuario();
        List<VwSelUsuario> lista = null;
        Integer currentPage = 0;
        Integer itemsPerPage = 0;
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(VwSelUsuario.class);
        DetachedCriteria detachedCriteria1 = DetachedCriteria.forClass(VwSelUsuario.class);
        Long nroUsuarios = new Long(0);
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
                } else if (parametro.getNombre().equals("clave") && parametro.getValor() != null) {
                    detachedCriteria.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%"));
                    detachedCriteria1.add(Property.forName(parametro.getNombre()).like("%" + parametro.getValor() + "%"));
                } else if (parametro.getNombre().equals("idRol") && parametro.getValor() != null) {
                    detachedCriteria.add(Property.forName(parametro.getNombre()).eq(Integer.parseInt(parametro.getValor())));
                    detachedCriteria1.add(Property.forName(parametro.getNombre()).eq(Integer.parseInt(parametro.getValor())));
                } else if (parametro.getNombre().equals("idPuntoVenta") && parametro.getValor() != null) {
                    detachedCriteria.add(Property.forName(parametro.getNombre()).eq(Integer.parseInt(parametro.getValor())));
                    detachedCriteria1.add(Property.forName(parametro.getNombre()).eq(Integer.parseInt(parametro.getValor())));
                } else if (parametro.getNombre().equals("activo") && parametro.getValor() != null) {
                    detachedCriteria.add(Property.forName(parametro.getNombre()).eq(Boolean.parseBoolean(parametro.getValor())));
                    detachedCriteria1.add(Property.forName(parametro.getNombre()).eq(Boolean.parseBoolean(parametro.getValor())));
                }
            }
            lista = detachedCriteria.getExecutableCriteria(sesion).setFirstResult(currentPage).setMaxResults(itemsPerPage).addOrder(Order.desc("id")).list();
            nroUsuarios = (long) detachedCriteria1.getExecutableCriteria(sesion).setProjection(Projections.rowCount()).uniqueResult();

            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        listaUsuario.setUsuarios(lista);
        listaUsuario.setNroUsuarios(nroUsuarios);
        return listaUsuario;
    }

    @Override
    public long ingresarSistema(String nombre, String clave) {
        Usuario usuario = new Usuario();
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = sesion.createCriteria(Usuario.class);
            usuario = (Usuario) criteria.add(Restrictions.eq("nombre", nombre)).add(Restrictions.eq("clave", clave)).add(Restrictions.eq("activo", true)).uniqueResult();
            sesion.beginTransaction();
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario.getId();
    }

}
