package pe.controlador.mantenimiento;

import pe.controlador.administracion.*;
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
import pe.modelo.dao.mantenimiento.IProductoDao;
import pe.modelo.pojo.Producto;
import pe.modelo.pojo.Usuario;

@Controller
public class ProductoController {
    
    @Autowired
    JsonTransformer jsonTransformer;
    
    @Autowired
    IProductoDao productoDao;
    
    @RequestMapping(value = "/producto/listar", method = RequestMethod.GET, produces = "application/json")
    public void listar(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        System.out.println("listarrrrrrrrrrrrrrrrr");
        PrintWriter out = httpServletResponse.getWriter();
        try {
            List<Producto> lista = productoDao.listar();
            String jsonSalida = jsonTransformer.toJson(lista);
            
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            System.out.println("jsonSalida "+jsonSalida);
            System.out.println("listaProducto "+lista);
            
            out.println(jsonSalida);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            out.println("{\"RSP\":\"ERROR\",\"MSG\":\"" + ex.getMessage() + "\"}");
        }
        
    }
    
    @RequestMapping(value = "/producto/crear", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void crear(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) {
        try {
            System.out.println("insertttttttttttttttttttt");
            System.out.println("jsonEntrada "+jsonEntrada);
            Producto producto = (Producto) jsonTransformer.fromJson(jsonEntrada, Producto.class);
            System.out.println("Producto "+producto);
            producto.setFechaCreacion(new Date());
            System.out.println("Productooooooo "+producto);
            productoDao.crear(producto);
            if (producto.getId() > 0) {
                String jsonSalida = jsonTransformer.toJson(producto);
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                httpServletResponse.setContentType("application/json; charset=UTF-8");
                httpServletResponse.getWriter().println(jsonSalida);
            } else {
                String jsonSalida = jsonTransformer.toJson(producto);
                httpServletResponse.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
                httpServletResponse.setContentType("application/json; charset=UTF-8");
                httpServletResponse.getWriter().println(jsonSalida);
            }
            
            
        } catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("text/plain; charset=UTF-8");
            try {
                ex.printStackTrace(httpServletResponse.getWriter());
            } catch (IOException ex1) {
                Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    @RequestMapping(value = "/producto/editar", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void editar(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) throws IOException {
        PrintWriter out = httpServletResponse.getWriter();
        try {
            Producto producto = (Producto) jsonTransformer.fromJson(jsonEntrada, Producto.class);
            HttpSession session = httpServletRequest.getSession();
            Usuario usuario = (Usuario) jsonTransformer.fromJson(session.getAttribute("session_usuario").toString(), Usuario.class);
            producto.setUsuarioByIdUsuarioModificacion(usuario);
            producto.setFechaModificacion(new Date());
            productoDao.editar(producto);
            String jsonSalida = jsonTransformer.toJson(producto);
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

    @RequestMapping(value = "/producto/eliminar/{id}", method = RequestMethod.POST, produces = "application/json")
    public void eliminar(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("id") long id) throws IOException {
        PrintWriter out = httpServletResponse.getWriter();
        try {
            productoDao.eliminar(id);
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
    
    @RequestMapping(value = "/producto/autocomplete", method = RequestMethod.POST, produces = "application/json")
    public void autocomplete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) throws IOException {
        System.out.println("jsonEntrada "+jsonEntrada);
        PrintWriter out = httpServletResponse.getWriter();
        try {
            List autocomplete = productoDao.autocomplete(jsonEntrada);
            System.out.println("autocomplete " + autocomplete);
            String jsonSalida = jsonTransformer.toJson(autocomplete);
            System.out.println("jsonSalida " + jsonSalida);
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
