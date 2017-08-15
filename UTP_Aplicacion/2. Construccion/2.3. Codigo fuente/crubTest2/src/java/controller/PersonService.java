package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import controller.PersonDAO;
import entity.TblusuarioUsu;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestMapping;

//@Service
public class PersonService {

	//@Autowired
	
	@RequestMapping()
	public List<TblusuarioUsu> list(Integer offset, Integer maxResults){
            System.out.println("PersonService");
            System.out.println("list "+offset+" "+maxResults);
            PersonDAO personDAO =new PersonDAO();
            return personDAO.list(offset, maxResults);
                
	}
	
	public Long count(){
            PersonDAO personDAO =new PersonDAO();
            return personDAO.count();
	}
	
	/*public void save(){
		personDAO.save();
	}*/
	
}
