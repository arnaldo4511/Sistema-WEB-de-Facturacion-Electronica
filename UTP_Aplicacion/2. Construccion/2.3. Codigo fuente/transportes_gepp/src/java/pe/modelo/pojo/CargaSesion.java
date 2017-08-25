/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
//import pe.modelo.pojo.Empresa;
import pe.modelo.pojo.Rol;
import pe.modelo.pojo.Usuario;
import pe.modelo.pojo.PuntoVenta;

/**
 *
 * @author octavio
 */
public class CargaSesion {

    private Usuario usuario;
    private List<Rol> roles;
    private List<PuntoVenta> puntoVentas;
    private List<Unidad> unidades;
    private List<TipoDocumentoEntidad> tiposDocumentosEntidades;

    public CargaSesion() {
    }

    public CargaSesion(Usuario usuario, List<Rol> roles, List<PuntoVenta> puntoVentas, List<Unidad> unidades, List<TipoDocumentoEntidad> tiposDocumentosEntidades) {
        this.usuario = usuario;
        this.roles = roles;
        this.puntoVentas = puntoVentas;
        this.unidades = unidades;
        this.tiposDocumentosEntidades = tiposDocumentosEntidades;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Rol> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public List<PuntoVenta> getPuntoVentas() {
        return this.puntoVentas;
    }

    public void setPuntoVentas(List<PuntoVenta> puntoVentas) {
        this.puntoVentas = puntoVentas;
    }
    
    public List<Unidad> getUnidades() {
        return this.unidades;
    }

    public void setUnidades(List<Unidad> unidades) {
        this.unidades = unidades;
    }
    
    public List<TipoDocumentoEntidad> getTiposDocumentosEntidades() {
        return this.tiposDocumentosEntidades;
    }

    public void setTiposDocumentosEntidades(List<TipoDocumentoEntidad> tiposDocumentosEntidades) {
        this.tiposDocumentosEntidades = tiposDocumentosEntidades;
    }
}
