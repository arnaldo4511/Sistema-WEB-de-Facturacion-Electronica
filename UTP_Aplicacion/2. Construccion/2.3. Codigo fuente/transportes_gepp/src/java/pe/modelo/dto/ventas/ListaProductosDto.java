/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dto.ventas;

import java.util.List;
import pe.modelo.pojo.vista.VwSelProductos;

/**
 *
 * @author HP
 */
public class ListaProductosDto {
    
    private List<VwSelProductos> productos;
    private double nroProductos;

    public ListaProductosDto(List<VwSelProductos> productos, double nroProductos) {
        this.productos = productos;
        this.nroProductos = nroProductos;
    }

    public ListaProductosDto() {
    }

    public List<VwSelProductos> getProductos() {
        return productos;
    }

    public void setProductos(List<VwSelProductos> productos) {
        this.productos = productos;
    }

    public double getNroProductos() {
        return nroProductos;
    }

    public void setNroProductos(double nroProductos) {
        this.nroProductos = nroProductos;
    }
    
    
    
}
