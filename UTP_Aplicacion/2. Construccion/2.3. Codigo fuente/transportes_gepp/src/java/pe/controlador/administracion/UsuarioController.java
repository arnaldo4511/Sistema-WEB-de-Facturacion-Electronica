package pe.controlador.administracion;

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
import pe.controlador.JsonTransformer;
import pe.modelo.dao.administracion.ICargaSesionDao;
import pe.modelo.dao.administracion.IUsuarioDao;
import pe.modelo.dao.administracion.ListaUsuario;
import pe.modelo.dto.CargaSesionDto;
import pe.modelo.dto.ParametroDto;
import pe.modelo.pojo.Usuario;

@Controller
public class UsuarioController {

    @Autowired
    JsonTransformer jsonTransformer;

    @Autowired
    IUsuarioDao usuarioDao;

    @Autowired
    ICargaSesionDao cargaSesionDao;

    @RequestMapping(value = "/usuario/ingresarsistema/{nombre}/{clave}", method = RequestMethod.GET, produces = "application/json")
    public void ingresarSistema(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("nombre") String nombre, @PathVariable("clave") String clave) throws IOException {
        PrintWriter out = httpServletResponse.getWriter();
        try {
            Long id = usuarioDao.ingresarSistema(nombre, clave);
            if (id > 0) {
                HttpSession session = httpServletRequest.getSession();
                CargaSesionDto cargaSesion = cargaSesionDao.crear(id);
                String carga = jsonTransformer.toJson(cargaSesion);
                System.out.println("carga:" + carga);
                session.setAttribute("carga", carga);
            }
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            out.println("{\"RSP\":" + id + "}");

        } catch (Exception ex) {
            ex.printStackTrace();
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            out.println("{\"RSP\":\"ERROR\",\"MSG\":\"" + ex.getMessage() + "\"}");
        }
    }

    @RequestMapping(value = "/usuario/cerrarsesion", method = RequestMethod.GET, produces = "application/json")
    public void cerrarSesion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        HttpSession session = httpServletRequest.getSession();
        session.invalidate();
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath());
    }

    @RequestMapping(value = "/usuario/buscarsesion", method = RequestMethod.GET, produces = "application/json")
    public void buscarsesion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("carga") == null) {
            session.invalidate();
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath());
        } else {
            String carga = session.getAttribute("carga").toString();
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            httpServletResponse.getWriter().println(carga);
        }
    }

    @RequestMapping(value = "/usuario/listar", method = RequestMethod.POST, produces = "application/json")
    public void listar(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) throws IOException {
        PrintWriter out = httpServletResponse.getWriter();
        try {
            ParametroDto[] parametros = (ParametroDto[]) jsonTransformer.fromJson(jsonEntrada, ParametroDto[].class);
            ListaUsuario lista = usuarioDao.listar(parametros);
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

    @RequestMapping(value = "/usuario/crear", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void crear(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) {
        try {
            Usuario usuario = (Usuario) jsonTransformer.fromJson(jsonEntrada, Usuario.class);
            usuarioDao.crear(usuario);
            if (usuario.getId() > 0) {
                String jsonSalida = jsonTransformer.toJson(usuario);
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                httpServletResponse.setContentType("application/json; charset=UTF-8");
                httpServletResponse.getWriter().println(jsonSalida);
            } else {
                String jsonSalida = jsonTransformer.toJson(usuario);
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
                Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    @RequestMapping(value = "/usuario/editar", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void editar(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) throws IOException {
        PrintWriter out = httpServletResponse.getWriter();
        try {
            Usuario usuario = (Usuario) jsonTransformer.fromJson(jsonEntrada, Usuario.class);
            usuarioDao.editar(usuario);
            String jsonSalida = jsonTransformer.toJson(usuario);
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

    @RequestMapping(value = "/usuario/buscar/{id}", method = RequestMethod.POST, produces = "application/json")
    public void buscar(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("id") long id) throws IOException {
        PrintWriter out = httpServletResponse.getWriter();
        try {
            Usuario usuario = usuarioDao.buscar(id);
            String jsonSalida = jsonTransformer.toJson(usuario);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            out.println(jsonSalida);
        } catch (Exception ex) {
            ex.printStackTrace();
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("{\"RSP\":\"ERROR\",\"MSG\":\"" + ex.getMessage() + "\"}");
        }
    }

    @RequestMapping(value = "/usuario/eliminar/{id}", method = RequestMethod.POST, produces = "application/json")
    public void eliminar(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("id") long id) throws IOException {
        PrintWriter out = httpServletResponse.getWriter();
        try {
            usuarioDao.eliminar(id);
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
}
