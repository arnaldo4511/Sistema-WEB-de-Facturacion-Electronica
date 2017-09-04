/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.ventas;

import java.util.List;
import pe.modelo.pojo.PuntoVenta;

/**
 *
 * @author octavio
 */
public interface IPuntoVentaDao {

    public void crear(PuntoVenta puntoVenta);

    public void editar(PuntoVenta puntoVenta);

    public PuntoVenta buscar(long id);

    public void eliminar(long id);

    public List<PuntoVenta> listar();

}
