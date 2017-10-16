/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.pojo.vista;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "vw_sel_productos",
        schema = "almacen"
)
public class VwSelProductos implements java.io.Serializable {
    
    private Long id;
    private String nombre;
    private String descripcion;
    private String nombreUnidad;
    private Double precioVenta;
    private Double precioCompra;

    public VwSelProductos() {
    }
    
    @Id
    @Column(name = "id", insertable = false, updatable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name = "nombre", insertable = false, updatable = false)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Column(name = "descripcion", insertable = false, updatable = false)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Column(name = "nombre_unidad", insertable = false, updatable = false)
    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }
    
    @Column(name = "precio_venta", precision = 17, scale = 17, insertable = false, updatable = false)
    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }
    
    @Column(name = "precio_compra", precision = 17, scale = 17, insertable = false, updatable = false)
    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }
    
    

    
}
