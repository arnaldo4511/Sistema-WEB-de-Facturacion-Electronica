/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.TblusuarioUsu;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author HP
 */
public class UsuarioModel {

    //read
    /*@RequestMapping()
    public List<TblusuarioUsu> ListarUsuarios() {
        List<TblusuarioUsu> listUsuario = new ArrayList<TblusuarioUsu>();

        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            listUsuario = sesion.createCriteria(TblusuarioUsu.class).list();
            System.out.println("createCriteria " + sesion.createCriteria(TblusuarioUsu.class).list());
            sesion.getTransaction().commit();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return listUsuario;
    }*/

    

    // other funtion
    public TblusuarioUsu ValidarUsuarioModel(String usuNombre, String usuContrasenhia) {

        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        TblusuarioUsu usuario = new TblusuarioUsu();
        System.out.println("getProfesor");
        try {
            sesion.beginTransaction();
            //List empList = s.createCriteria(TblusuarioUsu.class).add(Restrictions.eq("usuNombre", usuNombre)).list();
            Criteria criteria = sesion.createCriteria(TblusuarioUsu.class);
            criteria.add(Restrictions.eq("usuNombre", usuNombre));
            criteria.add(Restrictions.eq("usuContrasenhia", usuContrasenhia));
            List<TblusuarioUsu> results = criteria.list();
            Iterator iterator = results.iterator();
            while (iterator.hasNext()) {
                usuario = (TblusuarioUsu) iterator.next();
                System.out.println("Result Name SI:" + usuario.getUsuNombre()+" "+usuario.getUsuContrasenhia());
            }
            sesion.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Result Name NO1");
            e.printStackTrace();
            sesion.getTransaction().rollback();
            System.out.println("Result Name NO2");
        }

        return usuario;

    }

}
