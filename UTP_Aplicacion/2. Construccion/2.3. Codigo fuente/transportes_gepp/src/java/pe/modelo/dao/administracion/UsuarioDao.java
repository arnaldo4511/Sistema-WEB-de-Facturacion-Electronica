/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.administracion;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pe.modelo.dao.HibernateUtil;
import pe.modelo.pojo.Usuario;

/**
 *
 * @author octavio
 */
public class UsuarioDao implements IUsuarioDao {

    @Override
    public boolean ingresarSistema(String nombre, String clave) {
        boolean valido = false;
        try {
            Session sesion = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = sesion.createCriteria(Usuario.class);
            List<Usuario> usuarios = criteria.add(Restrictions.eq("nombre", nombre)).add(Restrictions.eq("clave", clave)).list();
            sesion.beginTransaction();
            sesion.getTransaction().commit();
            sesion.close();
            if (usuarios.size() > 0) {
                valido = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valido;
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

}
