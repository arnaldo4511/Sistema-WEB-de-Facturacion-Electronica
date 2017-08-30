/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author octavio
 */
public class ParametroDto {

    @JsonProperty("nombre")
    private String nombre;
    @JsonProperty("valor")
    private String valor;

    public ParametroDto() {
    }

    public ParametroDto(String nombre, String valor) {
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
