/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.almacen;

import java.util.List;
import pe.modelo.pojo.Producto;

/**
 *
 * @author octavio
 */
public interface IProductoDao {

    public void crear(Producto producto);

    public void editar(Producto producto);

    public Producto buscar(long id);

    public void eliminar(long id);

    public List<Producto> listar();

    public List<Producto> autocompletar(String criterio);
}
