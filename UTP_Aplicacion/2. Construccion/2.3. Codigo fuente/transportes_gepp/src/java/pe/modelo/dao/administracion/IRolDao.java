/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.administracion;

import java.util.List;
import pe.modelo.pojo.Rol;

/**
 *
 * @author octavio
 */
public interface IRolDao {

    public void crear(Rol rol);

    public void editar(Rol rol);

    public Rol buscar(long id);

    public void eliminar(long id);

    public List<Rol> listar();

}
