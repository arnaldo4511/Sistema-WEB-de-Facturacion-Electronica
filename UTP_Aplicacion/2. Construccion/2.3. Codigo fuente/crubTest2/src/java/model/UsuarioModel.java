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
    @RequestMapping()
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
    }

    //create
    public void create(TblusuarioUsu p) {
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            sesion.save(p);
            sesion.getTransaction().commit();

        } catch (Exception e) {

            e.printStackTrace();
            sesion.getTransaction().rollback();
        }
    }

    // other funtion
    public TblusuarioUsu getProfesor(String usuNombre, String usuContrasenhia) {

        Session s = HibernateUtil.getSessionFactory().getCurrentSession();
        TblusuarioUsu p = new TblusuarioUsu();
        System.out.println("getProfesor");
        try {
            s.beginTransaction();
            //List empList = s.createCriteria(TblusuarioUsu.class).add(Restrictions.eq("usuNombre", usuNombre)).list();
            Criteria crit = s.createCriteria(TblusuarioUsu.class);
            crit.add(Restrictions.eq("usuNombre", usuNombre));
            crit.add(Restrictions.eq("usuContrasenhia", usuContrasenhia));
            List<TblusuarioUsu> results = crit.list();
            Iterator iter = results.iterator();
            while (iter.hasNext()) {
                p = (TblusuarioUsu) iter.next();
                System.out.println("Result Name SI:" + p.getUsuNombre()+" "+p.getUsuContrasenhia());
            }
            s.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Result Name NO1");
            e.printStackTrace();
            s.getTransaction().rollback();
            System.out.println("Result Name NO2");
        }

        return p;

    }

}
