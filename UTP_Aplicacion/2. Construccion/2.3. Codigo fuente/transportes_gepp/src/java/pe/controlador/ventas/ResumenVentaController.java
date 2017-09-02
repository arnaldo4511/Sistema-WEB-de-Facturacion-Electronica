package pe.controlador.ventas;

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
import pe.modelo.dao.ventas.IResumenVentaDao;
//import pe.modelo.pojo.Producto;
import pe.modelo.pojo.ResumenVentas;
import pe.modelo.pojo.Usuario;

@Controller
public class ResumenVentaController {
    
    @Autowired
    JsonTransformer jsonTransformer;
    
    @Autowired
    IResumenVentaDao resumenVentaDao;
    
    @RequestMapping(value = "/resumenventa/listar", method = RequestMethod.GET, produces = "application/json")
    public void listar(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        System.out.println("listarrrrrrrrrrrrrrrrr");
        PrintWriter out = httpServletResponse.getWriter();
        try {
            List<ResumenVentas> lista = resumenVentaDao.listar();
            String jsonSalida = jsonTransformer.toJson(lista);
            System.out.println("jsonSalida "+jsonSalida);
            System.out.println("listaProducto "+lista);
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
    
    @RequestMapping(value = "/resumenventa/crear", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void crear(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) {
        try {
            System.out.println("crearrrrrrrrrrrrrrrrrrrrrrr");
            System.out.println("jsonEntrada "+jsonEntrada);
            ResumenVentas resumenVentas = (ResumenVentas) jsonTransformer.fromJson(jsonEntrada, ResumenVentas.class);
            System.out.println("ResumenVentas "+resumenVentas);
            resumenVentas.setFechaCreacion(new Date());
            System.out.println("ResumenVentasss "+resumenVentas);
            resumenVentaDao.crear(resumenVentas);
            if (resumenVentas.getId() > 0) {
                String jsonSalida = jsonTransformer.toJson(resumenVentas);
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                httpServletResponse.setContentType("application/json; charset=UTF-8");
                httpServletResponse.getWriter().println(jsonSalida);
            } else {
                String jsonSalida = jsonTransformer.toJson(resumenVentas);
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
                Logger.getLogger(ResumenVentaController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    /*@RequestMapping(value = "/producto/editar", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void editar(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) throws IOException {
        PrintWriter out = httpServletResponse.getWriter();
        try {
            Producto producto = (Producto) jsonTransformer.fromJson(jsonEntrada, Producto.class);
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
        
    }*/
}
