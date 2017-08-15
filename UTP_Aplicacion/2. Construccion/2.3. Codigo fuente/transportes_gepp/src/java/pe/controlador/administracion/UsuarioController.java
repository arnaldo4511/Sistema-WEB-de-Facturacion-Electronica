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
import pe.modelo.dao.administracion.IUsuarioDao;

@Controller
public class UsuarioController {

    @Autowired
    JsonTransformer jsonTransformer;

    @Autowired
    IUsuarioDao usuarioDao;

    @RequestMapping(value = "/Usuario/ingresarSistema/{nombre}/{clave}", method = RequestMethod.GET, produces = "application/json")
    public void ingresarSistema(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("nombre") String nombre, @PathVariable("clave") String clave) {
        try {
            
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println("{\"RSP\":\"OK\"}");

        } catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        }
    }
}
