/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.ventas;

import pe.modelo.dao.publico.*;
import java.util.Date;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import pe.modelo.dao.HibernateUtil;
import pe.modelo.pojo.Cliente;
import pe.modelo.pojo.Entidad;
import pe.modelo.pojo.vista.VwAutCliente;

/**
 *
 * @author octavio
 */
public class ClienteDao implements IClienteDao {

    @Override
    public void crear(Cliente cliente) {
        try {
            System.out.println("cliente " + cliente);
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.save(cliente.getEntidad());
            sesion.save(cliente);
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editar(Cliente cliente) {
        try {
            cliente.setFechaModificacion(new Date());
            cliente.getEntidad().setFechaModificacion(new Date());
            cliente.getEntidad().setUsuarioByIdUsuarioModificacion(cliente.getUsuarioByIdUsuarioModificacion());
            cliente.getEntidad().setFechaModificacion(new Date());

            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            cliente.getEntidad().setUsuarioByIdUsuarioCreacion((cliente.getUsuarioByIdUsuarioCreacion()));
            //System.out.println("numero:" + cliente.getEntidad().getDocumento());
            /*if (cliente.getEntidad().getId() > 0) {
                Entidad entidad = (Entidad) sesion.load(Entidad.class, cliente.getEntidad().getId());
                cliente.getEntidad().setUsuarioByIdUsuarioCreacion((entidad.getUsuarioByIdUsuarioCreacion()));
            }*/
            sesion.update(cliente);
            sesion.update(cliente.getEntidad());
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cliente buscar(long id) {
        Cliente cliente = null;
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            cliente = (Cliente) sesion.load(Cliente.class, id);
            Hibernate.initialize(cliente);
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cliente;
    }

    @Override
    public void eliminar(long id) {
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            String hql = "delete from Cliente where id= :id";
            sesion.createQuery(hql).setLong("id", id).executeUpdate();
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Cliente> listar() {
        List<Cliente> lista = null;
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            Query query = sesion.createQuery("FROM Cliente order by id");
            lista = query.list();
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<VwAutCliente> autocompletar(String criterio, String codigoTipoDocumentoEntidad) {
        List<VwAutCliente> lista = null;
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            Query query = sesion.createQuery("FROM VwAutCliente WHERE (UPPER(documento) LIKE UPPER(:criterio) OR UPPER(nombre) LIKE UPPER(:criterio)) AND codigoTipoDocumentoEntidad=:codigoTipoDocumentoEntidad order by id")
                    .setString("criterio", "%" + criterio + "%")
                    .setString("codigoTipoDocumentoEntidad", codigoTipoDocumentoEntidad).setMaxResults(10);
            lista = query.list();
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
