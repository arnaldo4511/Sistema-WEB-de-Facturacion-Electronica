package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import controller.PersonService;

@Controller
//@RequestMapping(value="/person")
public class PersonController {

    //@Autowired
    

    @RequestMapping(value = "/list")
    public String list(Model model, Integer offset, Integer maxResults) {
        System.out.println("listtttt"+offset);
        PersonService personService=new PersonService();
        model.addAttribute("persons", personService.list(offset, maxResults));
        model.addAttribute("count", personService.count());
        model.addAttribute("offset", offset);
        System.out.println("model "+model);
        return "/list";
    }

    /*@RequestMapping(value="/save")
     public String save(){
     personService.save();
     return "/person/list";
     }*/
}
