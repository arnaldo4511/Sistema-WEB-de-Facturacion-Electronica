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
import pe.modelo.pojo.vista.VwAutCliente;

@Controller
public class ClienteController {

    @Autowired
    JsonTransformer jsonTransformer;

    @Autowired
    IClienteDao clienteDao;

    @RequestMapping(value = "/cliente/autocompletar/{criterio}/{codigotipodocumentoentidad}", method = RequestMethod.GET, produces = "application/json")
    public void autocompletar(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("criterio") String criterio, @PathVariable("codigotipodocumentoentidad") String codigoTipoDocumentoEntidad) throws IOException {
        PrintWriter out = httpServletResponse.getWriter();
        try {
            List<VwAutCliente> lista = clienteDao.autocompletar(criterio, codigoTipoDocumentoEntidad);
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

    @RequestMapping(value = "/cliente/listar", method = RequestMethod.GET, produces = "application/json")
    public void listar(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        System.out.println("listarrrrrrrrrrrrrrrrr");
        PrintWriter out = httpServletResponse.getWriter();
        try {
            List<Cliente> lista = clienteDao.listar();
            String jsonSalida = jsonTransformer.toJson(lista);
            System.out.println("jsonSalida " + jsonSalida);
            System.out.println("listaCliente " + lista);
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

    @RequestMapping(value = "/cliente/crear", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void crear(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) throws IOException {
        try {
            //System.out.println("insertttttttttttttttttttt");
            System.out.println("jsonEntrada " + jsonEntrada);
            Cliente cliente = (Cliente) jsonTransformer.fromJson(jsonEntrada, Cliente.class);
            //System.out.println("entidad " + cliente);
            cliente.getEntidad().setFechaCreacion(new Date());
            cliente.setFechaCreacion(new Date());
            //cliente.setFechaCreacion(new Date());
            //System.out.println("entidadddd " + cliente);
            clienteDao.crear(cliente);
            if (cliente.getId() > 0) {
                String jsonSalida = jsonTransformer.toJson(cliente);
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                httpServletResponse.setContentType("application/json; charset=UTF-8");
                httpServletResponse.getWriter().println(jsonSalida);
            } else {
                String jsonSalida = jsonTransformer.toJson(cliente);
                httpServletResponse.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                httpServletResponse.setContentType("application/json; charset=UTF-8");
                httpServletResponse.getWriter().println(jsonSalida);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("text/plain; charset=UTF-8");
            httpServletResponse.getWriter().println(ex.getMessage());
        }
    }

    @RequestMapping(value = "/cliente/eliminar/{id}", method = RequestMethod.POST, produces = "application/json")
    public void eliminar(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("id") long id) throws IOException {
        PrintWriter out = httpServletResponse.getWriter();
        try {
            System.out.println("id " + id);
            clienteDao.eliminar(id);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            out.println(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            out.println("{\"RSP\":\"ERROR\",\"MSG\":\"" + ex.getMessage() + "\"}");
        }
    }

    @RequestMapping(value = "/cliente/editar", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void editar(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) throws IOException {
        PrintWriter out = httpServletResponse.getWriter();
        try {
            System.out.println("jsonEntrada " + jsonEntrada);
            Cliente cliente = (Cliente) jsonTransformer.fromJson(jsonEntrada, Cliente.class);
            System.out.println("cliente " + cliente);
            clienteDao.editar(cliente);
            String jsonSalida = jsonTransformer.toJson(cliente);
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

    @RequestMapping(value = "/cliente/buscar/{id}", method = RequestMethod.POST, produces = "application/json")
    public void buscar(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("id") long id) throws IOException {
        try {
            Cliente cliente = clienteDao.buscar(id);

            String jsonSalida = jsonTransformer.toJson(cliente);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(jsonSalida);

        } catch (Exception ex) {
            ex.printStackTrace();
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("text/plain; charset=UTF-8");
            httpServletResponse.getWriter().println(ex.getMessage());
        }
    }
}
