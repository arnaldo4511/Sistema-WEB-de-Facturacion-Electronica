/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.administracion;

import java.util.List;
import pe.modelo.dto.ParametroDto;
import pe.modelo.pojo.Rol;
import pe.modelo.pojo.RolMenu;

/**
 *
 * @author octavio
 */
public interface IRolDao {

    public void crear(Rol rol);

    public void editar(Rol rol);

    public Rol buscar(long id);

    public void eliminar(long id);

    public ListaRol listar(ParametroDto[] parametros);

    public List<RolMenu> listarRolMenu();

}
