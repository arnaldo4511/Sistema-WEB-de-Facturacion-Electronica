/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.mantenimiento;

import pe.modelo.dao.mantenimiento.*;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import pe.modelo.dao.HibernateUtil;
import pe.modelo.pojo.Cliente;
import pe.modelo.pojo.Entidad;

/**
 *
 * @author HP
 */
public class ClienteDao implements IClienteDao {

    @Override
    public List<Cliente> listar() {
        List<Cliente> lista = null;
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            Query query = sesion.createQuery("from Cliente order by id");
            lista = query.list();
            /*Criteria accountCriteria = sesion.createCriteria(Entidad.class,"acc");
            Criteria bookCriteria =  accountCriteria .createCriteria("cliente","b");
            
            ProjectionList properties = Projections.projectionList();
            properties.add(Projections.property("documento"));
            properties.add(Projections.property("nombre"));
            
            accountCriteria.setProjection(properties);
            accountCriteria.list();
            
            lista = accountCriteria.list();*/
            
            sesion.getTransaction().commit();
            sesion.close();
            //System.out.println("accountCriteria " + accountCriteria);
            System.out.println("listar " + lista);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    @Override
    public void crear(Cliente cliente) {
        try {
            System.out.println("entidadCrear " + cliente);
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.save(cliente.getEntidad());
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public void editar(Cliente cliente) {
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            String hqlUpdate = "update Entidad set nombre = :nombre,documento = :documento,tipoDocumentoEntidad = :tipoDocumentoEntidad,direccion = :direccion,correoElectronico1 =:correoElectronico1,correoElectronico2 =:correoElectronico2,correoElectronico3 =:correoElectronico3 where id = :id";
            sesion.createQuery(hqlUpdate)
                    .setString("nombre", cliente.getEntidad().getNombre())
                    .setString("documento", cliente.getEntidad().getDocumento())
                    .setString("tipoDocumentoEntidad", cliente.getEntidad().getTipoDocumentoEntidad().getCodigo())
                    .setString("direccion", cliente.getEntidad().getDireccion())
                    .setString("correoElectronico1", cliente.getEntidad().getCorreoElectronico1())
                    .setString("correoElectronico2", cliente.getEntidad().getCorreoElectronico2())
                    .setString("correoElectronico3", cliente.getEntidad().getCorreoElectronico3())
                    .setLong("id", cliente.getEntidad().getId())
                    .executeUpdate();
            sesion.getTransaction().commit();
            sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
