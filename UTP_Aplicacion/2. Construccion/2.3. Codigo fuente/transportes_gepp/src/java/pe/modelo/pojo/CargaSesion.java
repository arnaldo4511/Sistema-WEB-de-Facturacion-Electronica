/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.pojo;

import java.util.List;
import pe.modelo.pojo.Empresa;
import pe.modelo.pojo.Rol;
import pe.modelo.pojo.Usuario;

/**
 *
 * @author octavio
 */
public class CargaSesion {

    private Usuario usuario;
    private List<Rol> roles;
    private List<Empresa> empresas;

    public CargaSesion() {
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

    public List<Empresa> getEmpresas() {
        return this.empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }
}
