/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.administracion;

import java.util.List;
import pe.modelo.dto.ParametroDto;
import pe.modelo.pojo.Usuario;

/**
 *
 * @author octavio
 */
public interface IUsuarioDao {

    public void crear(Usuario usuario);

    public void editar(Usuario usuario);

    public Usuario buscar(long id);

    public void eliminar(long id);

    public ListaUsuario listar(ParametroDto[] parametros);

    public long ingresarSistema(String nombre, String clave);

}
