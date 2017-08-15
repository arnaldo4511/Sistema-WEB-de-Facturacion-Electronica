/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.UsuarioModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.ModelAndView;

/**
 *
 * @author HP
 */
@Controller
public class UsuarioController {
    
    /*@RequestMapping(value="getAll",method = RequestMethod.GET)
    public String getAll(Model m)
    {
        
        UsuarioModel usuarioModel= new UsuarioModel();
        m.addAttribute("lst",usuarioModel.ListarUsuarios());
        return "data";
    }*/
    
    @RequestMapping(value = "/usuarioVista")
    public String listarUsuariosCtll(Model model, Integer offset, Integer maxResults) {
        System.out.println("listtttt"+offset);
        UsuarioService usuarioService=new UsuarioService();
        model.addAttribute("persons", usuarioService.listarUsuarioServ(offset, maxResults));
        model.addAttribute("count", usuarioService.count());
        model.addAttribute("offset", offset);
        System.out.println("model "+model);
        return "/usuarioVista";
    }
}
