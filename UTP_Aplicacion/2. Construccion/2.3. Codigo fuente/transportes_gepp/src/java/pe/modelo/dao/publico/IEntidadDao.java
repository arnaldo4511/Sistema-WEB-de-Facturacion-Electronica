/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.publico;

import java.util.List;
import pe.modelo.pojo.Entidad;

/**
 *
 * @author octavio
 */
public interface IEntidadDao {

    public void crear(Entidad entidad);

    public void editar(Entidad entidad);

    public Entidad buscar(long id);

    public void eliminar(long id);

    public List<Entidad> listar();

}
