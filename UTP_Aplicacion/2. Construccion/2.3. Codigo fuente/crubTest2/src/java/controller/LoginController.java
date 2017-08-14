/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.TblusuarioUsu;
import model.UsuarioModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;

/**
 *
 * @author HP
 */
@Controller
public class LoginController {

    @RequestMapping("login")
    public ModelAndView redireccio() {

        ModelAndView MV = new ModelAndView();
        MV.setView("login");
    //  MV.addObject("mensaje","Hola soy un mensaje desde copntrolador");

        return MV;
    }
    
    @RequestMapping(value = "ValidarUsuario", method = RequestMethod.GET)
    public String ValidarUsuario(@ModelAttribute(value = "Usuario") TblusuarioUsu usuarioString,Model m){
        
        System.out.println("usuarioString "+usuarioString.getUsuNombre()+" "+usuarioString.getUsuContrasenhia());
        UsuarioModel model= new UsuarioModel();
        TblusuarioUsu usuario = new TblusuarioUsu();
        usuario = model.getProfesor(usuarioString.getUsuNombre(),usuarioString.getUsuContrasenhia());
        
        System.out.println("Result Name SI Controller:" + usuario.getUsuNombre()+" "+usuario.getUsuContrasenhia());
        if(usuario.getUsuNombre().equals(usuarioString.getUsuNombre()) && usuario.getUsuContrasenhia().equals(usuarioString.getUsuContrasenhia())){
            System.out.println("SI");
        }
        
        m.addAttribute("usuarioAttrib",usuario);
        return "menu";
    }
    /*
    @RequestMapping(value = "update",method=RequestMethod.POST)
    public String update(@ModelAttribute(value="Profesor") Profesor p)
    {
        
        
       ProfesorModel model= new ProfesorModel();
       DepartamentoModel dpmodel=new DepartamentoModel();
       Departamento dp=new Departamento();
       Profesor aux= new Profesor();
        aux=model.getProfesor(p.getId());
        aux.setNombre(p.getNombre());
        aux.setCorreo(p.getCorreo());
        aux.setTelefono(p.getTelefono());
        aux.setNacimiento(p.getNacimiento());
        aux.setDireccion(p.getDireccion());
           // aux.setDepartamento(dp);
        model.edit(aux);
        return "redirect:getAll.html";
    }*/

}
