package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import controller.UsuarioDAO;
import entity.TblusuarioUsu;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestMapping;

//@Service
public class UsuarioService {

	//@Autowired
	
	@RequestMapping()
	public List<TblusuarioUsu> listarUsuarioServ(Integer offset, Integer maxResults){
            System.out.println("PersonService");
            System.out.println("list "+offset+" "+maxResults);
            UsuarioDAO usuarioDAO =new UsuarioDAO();
            return usuarioDAO.listarUsuariosDAO(offset, maxResults);
                
	}
	
	public Long count(){
            UsuarioDAO usuarioDAO =new UsuarioDAO();
            return usuarioDAO.count();
	}
	
	/*public void save(){
		personDAO.save();
	}*/
	
}
