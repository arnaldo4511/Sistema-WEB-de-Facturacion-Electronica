/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.ventas;

import java.util.List;
import pe.modelo.pojo.Producto;
import pe.modelo.pojo.ResumenVentas;

/**
 *
 * @author HP
 */
public interface IResumenVentaDao {

    public void crear(ResumenVentas resumenVentas);
    
    /*public void editar(Producto producto);

    public Producto buscar(long id);

    public void eliminar(long id);*/

    public List<ResumenVentas> listar();
    
    //public List<String> autocomplete(String nombre);
    

}
