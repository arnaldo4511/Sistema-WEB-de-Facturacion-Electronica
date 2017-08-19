/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.publico;

import java.util.Date;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import pe.modelo.dao.HibernateUtil;
import pe.modelo.pojo.Empresa;

/**
 *
 * @author octavio
 */
public class EmpresaDao implements IEmpresaDao {

    @Override
    public void crear(Empresa empresa) {
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.save(empresa);
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editar(Empresa empresa) {
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            String hqlUpdate = "update Empresa set nombre = :nombre,descripcion = :descripcion,admin = :admin where id = :id";
            /*sesion.createQuery(hqlUpdate)
                    .setString("nombre", empresa.getNombre())
                    .setString("descripcion", empresa.getDescripcion())
                    .setBoolean("admin", empresa.isAdmin())
                    .setLong("id", empresa.getId())
                    .executeUpdate();*/
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Empresa buscar(long id) {
        Empresa empresa = null;
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            empresa = (Empresa) sesion.load(Empresa.class, id);
            Hibernate.initialize(empresa);
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empresa;
    }

    @Override
    public void eliminar(long id) {
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            String hql = "delete from Empresa where id= :id";
            sesion.createQuery(hql).setLong("id", id).executeUpdate();
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Empresa> listar() {
        List<Empresa> lista = null;
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            Query query = sesion.createQuery("from Empresa order by id");
            lista = query.list();
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
