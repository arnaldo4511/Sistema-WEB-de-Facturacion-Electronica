/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dto.ventas;

import java.util.List;
import pe.modelo.pojo.vista.VwSelClientes;

/**
 *
 * @author HP
 */
public class ListaClientesDto {

    private List<VwSelClientes> clientes;
    private double nroClientes;

    public ListaClientesDto(List<VwSelClientes> clientes, double nroClientes) {
        this.clientes = clientes;
        this.nroClientes = nroClientes;
        System.out.println("ListaClientesDto ");
    }

    public ListaClientesDto() {
    }

    public List<VwSelClientes> getClientes() {
        return clientes;
    }

    public void setClientes(List<VwSelClientes> clientes) {
        this.clientes = clientes;
    }

    public double getNroClientes() {
        return nroClientes;
    }

    public void setNroClientes(double nroClientes) {
        this.nroClientes = nroClientes;
    }

}
