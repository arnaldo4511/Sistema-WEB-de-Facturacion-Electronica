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

/**
 *
 * @author octavio
 */
public class CargaSesion {

    private Usuario usuario;
    private List<Rol> roles;

    public CargaSesion() {
    }

    public CargaSesion(Usuario usuario, List<Rol> roles, List<Empresa> empresas) {
        this.usuario = usuario;
        this.roles = roles;
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
}
