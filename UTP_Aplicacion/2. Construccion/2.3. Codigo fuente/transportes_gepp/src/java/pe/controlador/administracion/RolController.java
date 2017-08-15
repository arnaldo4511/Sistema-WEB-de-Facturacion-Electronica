package pe.controlador.administracion;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pe.controlador.BussinessException;
import pe.controlador.BussinessMessage;
import pe.controlador.JsonTransformer;
import pe.modelo.dao.administracion.IRolDao;
import pe.modelo.pojo.Rol;

@Controller
public class RolController {

    @Autowired
    JsonTransformer jsonTransformer;

    @Autowired
    IRolDao rolDao;

    @RequestMapping(value = "/Rol/listar", method = RequestMethod.GET, produces = "application/json")
    public void find(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            List<Rol> lista = rolDao.listar();
            String jsonSalida = jsonTransformer.toJson(lista);
            System.out.println("jsonSalida:"+jsonSalida);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonSalida);

        } catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }
}
