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
@Table(name = "vw_sel_clientes",
        schema = "ventas"
)
public class VwSelClientes implements java.io.Serializable {
    
    private Long id;
    private String nombreTipo;
    private String documento;
    private String nombre;
    private String direccion;
    
    public VwSelClientes() {
        System.out.println("VwSelClientes ");
    }
    
    @Id
    @Column(name = "id", insertable = false, updatable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name = "nombre_tipo", insertable = false, updatable = false)
    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }
    
    @Column(name = "documento", length = 11, insertable = false, updatable = false)
    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
    
    @Column(name = "nombre", insertable = false, updatable = false)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Column(name = "direccion", length = 250, insertable = false, updatable = false)
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
    
}
