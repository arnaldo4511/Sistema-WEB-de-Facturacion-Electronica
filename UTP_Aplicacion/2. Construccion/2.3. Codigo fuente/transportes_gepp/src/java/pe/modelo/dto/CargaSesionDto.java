/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import pe.modelo.pojo.EstadoDocumentoVenta;
import pe.modelo.pojo.Producto;
//import pe.modelo.pojo.Empresa;
import pe.modelo.pojo.Rol;
import pe.modelo.pojo.Usuario;
import pe.modelo.pojo.PuntoVenta;
import pe.modelo.pojo.PuntoVenta;
import pe.modelo.pojo.PuntoVentaSerie;
import pe.modelo.pojo.Rol;
import pe.modelo.pojo.TipoDocumentoEntidad;
import pe.modelo.pojo.TipoDocumentoVenta;
import pe.modelo.pojo.TipoNotaCredito;
import pe.modelo.pojo.TipoNotaDebito;
import pe.modelo.pojo.Unidad;
import pe.modelo.pojo.Usuario;
import pe.modelo.pojo.Vehiculo;

/**
 *
 * @author octavio
 */
public class CargaSesionDto {

    private Usuario usuario;
    private List<Rol> roles;
    private List<PuntoVenta> puntoVentas;
    private List<Unidad> unidades;
    private List<TipoDocumentoEntidad> tipoDocumentoEntidads;
    //Ventas
    private List<EstadoDocumentoVenta> estadoDocumentoVentas;
    private List<TipoDocumentoVenta> tipoDocumentoVentas;
    private List<TipoNotaCredito> tipoNotaCreditos;
    private List<TipoNotaDebito> tipoNotaDebitos;
    private List<PuntoVentaSerie> puntoVentaSeries;
    private List<Vehiculo> vehiculos;

    //Almacen
    private List<Producto> productos;

    public CargaSesionDto() {
    }

    public CargaSesionDto(Usuario usuario, List<Rol> roles, List<PuntoVenta> puntoVentas, List<Unidad> unidades, List<TipoDocumentoEntidad> tipoDocumentoEntidads) {
        this.usuario = usuario;
        this.roles = roles;
        this.puntoVentas = puntoVentas;
        this.unidades = unidades;
        this.tipoDocumentoEntidads = tipoDocumentoEntidads;
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

    public List<TipoDocumentoEntidad> getTipoDocumentoEntidads() {
        return this.tipoDocumentoEntidads;
    }

    public void setTipoDocumentoEntidads(List<TipoDocumentoEntidad> tipoDocumentoEntidads) {
        this.tipoDocumentoEntidads = tipoDocumentoEntidads;
    }

    public List<TipoDocumentoVenta> getTipoDocumentoVentas() {
        return this.tipoDocumentoVentas;
    }

    public void setTipoDocumentoVentas(List<TipoDocumentoVenta> tipoDocumentoVentas) {
        this.tipoDocumentoVentas = tipoDocumentoVentas;
    }

    public List<TipoNotaCredito> getTipoNotaCreditos() {
        return this.tipoNotaCreditos;
    }

    public void setTipoNotaCreditos(List<TipoNotaCredito> tipoNotaCreditos) {
        this.tipoNotaCreditos = tipoNotaCreditos;
    }

    public List<TipoNotaDebito> getTipoNotaDebitos() {
        return this.tipoNotaDebitos;
    }

    public void setTipoNotaDebitos(List<TipoNotaDebito> tipoNotaDebitos) {
        this.tipoNotaDebitos = tipoNotaDebitos;
    }

    public List<EstadoDocumentoVenta> getEstadoDocumentoVentas() {
        return estadoDocumentoVentas;
    }

    public void setEstadoDocumentoVentas(List<EstadoDocumentoVenta> estadoDocumentoVentas) {
        this.estadoDocumentoVentas = estadoDocumentoVentas;
    }

    public List<PuntoVentaSerie> getPuntoVentaSeries() {
        return puntoVentaSeries;
    }

    public void setPuntoVentaSeries(List<PuntoVentaSerie> puntoVentaSeries) {
        this.puntoVentaSeries = puntoVentaSeries;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
}
