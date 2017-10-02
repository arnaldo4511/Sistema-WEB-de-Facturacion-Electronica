/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.administracion;

import pe.modelo.pojo.vista.VwSelUsuario;
import java.util.List;

/**
 *
 * @author octavio
 */
public class ListaUsuario {

    private List<VwSelUsuario> usuarios;
    private Long nroUsuarios;

    public ListaUsuario(List<VwSelUsuario> usuarios, Long nroUsuarios) {
        this.usuarios = usuarios;
        this.nroUsuarios = nroUsuarios;
    }

    ListaUsuario() {
    }

    public List<VwSelUsuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<VwSelUsuario> usuarios) {
        this.usuarios = usuarios;
    }

    public double getNroUsuarios() {
        return nroUsuarios;
    }

    public void setNroUsuarios(Long nroUsuarios) {
        this.nroUsuarios = nroUsuarios;
    }

}
