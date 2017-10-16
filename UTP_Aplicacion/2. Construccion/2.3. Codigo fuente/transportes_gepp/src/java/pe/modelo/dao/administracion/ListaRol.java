/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.administracion;

import pe.modelo.pojo.vista.VwSelRol;
import java.util.List;

/**
 *
 * @author octavio
 */
public class ListaRol {

    private List<VwSelRol> roles;
    private double nroRoles;

    public ListaRol() {
    }

    public ListaRol(List<VwSelRol> roles, double nroRoles) {
        this.roles = roles;
        this.nroRoles = nroRoles;
    }

    public List<VwSelRol> getRoles() {
        return roles;
    }

    public void setRoles(List<VwSelRol> roles) {
        this.roles = roles;
    }

    public double getNroRoles() {
        return nroRoles;
    }

    public void setNroRoles(double nroRoles) {
        this.nroRoles = nroRoles;
    }

}
