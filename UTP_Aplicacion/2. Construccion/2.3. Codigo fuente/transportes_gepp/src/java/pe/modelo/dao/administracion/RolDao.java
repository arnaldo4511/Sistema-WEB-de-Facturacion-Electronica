/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.administracion;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pe.modelo.dao.HibernateUtil;
import pe.modelo.pojo.Rol;

/**
 *
 * @author octavio
 */
public class RolDao implements IRolDao {
    
    @Override
    public List<Rol> listar() {
        List<Rol> lista = null;
        try {
            Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
            sesion.beginTransaction();
            Query query = sesion.createQuery("from Rol");
            lista = query.list();
            sesion.getTransaction().commit();
            System.out.print("lista:" + lista);
            //sesion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
}
