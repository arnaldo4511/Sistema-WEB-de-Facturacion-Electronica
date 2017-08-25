package pe.controlador.ventas;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
import pe.modelo.dao.ventas.IClienteDao;
import pe.modelo.pojo.Cliente;

@Controller
public class ClienteController {

    @Autowired
    JsonTransformer jsonTransformer;

    @Autowired
    IClienteDao clienteDao;

    @RequestMapping(value = "/cliente/listar/", method = RequestMethod.GET, produces = "application/json")
    public void listar(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        PrintWriter out = httpServletResponse.getWriter();
        try {
            List<Cliente> lista = clienteDao.listar();
            String jsonSalida = jsonTransformer.toJson(lista);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            out.println(jsonSalida);

        } catch (Exception ex) {
            ex.printStackTrace();
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            out.println("{\"RSP\":\"ERROR\",\"MSG\":\"" + ex.getMessage() + "\"}");
        }

    }

    @RequestMapping(value = "/cliente/autocompletar/{criterio}", method = RequestMethod.GET, produces = "application/json")
    public void autocompletar(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("criterio") String criterio) throws IOException {
        PrintWriter out = httpServletResponse.getWriter();
        try {
            List<Cliente> lista = clienteDao.autocompletar(criterio);
            String jsonSalida = jsonTransformer.toJson(lista);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            out.println(jsonSalida);

        } catch (Exception ex) {
            ex.printStackTrace();
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            out.println("{\"RSP\":\"ERROR\",\"MSG\":\"" + ex.getMessage() + "\"}");
        }

    }
}
