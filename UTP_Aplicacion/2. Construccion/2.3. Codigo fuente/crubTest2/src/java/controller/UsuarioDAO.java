package controller;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entity.TblusuarioUsu;
import java.util.ArrayList;
import model.HibernateUtil;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.RequestMapping;

/*@Repository
 @Transactional*/
public class UsuarioDAO {

	//@Autowired 
    //private SessionFactory sessionFactory;
	//@SuppressWarnings("unchecked")
    //@Transactional
    @RequestMapping()
    public List<TblusuarioUsu> listarUsuariosDAO(Integer offset, Integer maxResults) {
        /*return sessionFactory.openSession()
         .createCriteria(TblusuarioUsu.class)
         .setFirstResult(offset!=null?offset:0)
         .setMaxResults(maxResults!=null?maxResults:10)
         .list();*/
        System.out.println("PersonDAO");

        List<TblusuarioUsu> listUsuario = new ArrayList<TblusuarioUsu>();

        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            listUsuario = sesion.createCriteria(TblusuarioUsu.class).setFirstResult(offset != null ? offset : 0)
                    .setMaxResults(maxResults != null ? maxResults : 10)
                    .list();
            System.out.println("createCriteria " + sesion.createCriteria(TblusuarioUsu.class).list());
            sesion.getTransaction().commit();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return listUsuario;
    }

    public Long count() {
        ///return (Long) sessionFactory.openSession()
                //.createCriteria(TblusuarioUsu.class)
                //.setProjection(Projections.rowCount())
                //.uniqueResult();
        
        Long listUsuarioCount =0L;

        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            sesion.beginTransaction();
            listUsuarioCount = (Long)sesion.createCriteria(TblusuarioUsu.class).setProjection(Projections.rowCount())
                .uniqueResult();
            System.out.println("createCriteria " + sesion.createCriteria(TblusuarioUsu.class).list());
            sesion.getTransaction().commit();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return listUsuarioCount;

    }

    /*public void save(){
     for(int itr=1;itr <= 100 ; itr++){
     Person person = new Person("Person_"+itr,Math.max(25, (itr%2)*35));
     sessionFactory.openSession()
     .save(person);
     }
		
		
		
     }*/
}
