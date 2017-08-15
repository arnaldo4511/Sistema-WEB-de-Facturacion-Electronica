package pe.controlador.administracion;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import pe.modelo.pojo.Usuario;

@Controller
public class UsuarioController {

    @Autowired
    JsonTransformer jsonTransformer;

    @Autowired
    IUsuarioDao usuarioDao;

    @RequestMapping(value = "/usuario/ingresarSistema/{nombre}/{clave}", method = RequestMethod.GET, produces = "application/json")
    public void ingresarSistema(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("nombre") String nombre, @PathVariable("clave") String clave) throws IOException {
        PrintWriter out = httpServletResponse.getWriter();
        try {
            boolean valido = usuarioDao.ingresarSistema(nombre, clave);
            if (valido) {
                Usuario usuario = usuarioDao.buscar(3);
                HttpSession session = httpServletRequest.getSession();
                session.setAttribute("session", true);
                session.setAttribute("session_nombre", nombre);
                session.setAttribute("session_usuario", jsonTransformer.toJson(usuario));
            }
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            out.println("{\"RSP\":" + valido + "}");

        } catch (Exception ex) {
            ex.printStackTrace();
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            out.println("{\"RSP\":\"ERROR\",\"MSG\":\""+ex.getMessage()+"\"}");
        }
    }
}
