/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dto.ventas;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author octavio
 */
public class Parametro {

    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("valor")
    private String valor;

    public Parametro() {
    }

    public Parametro(String nombre, String valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return this.valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
