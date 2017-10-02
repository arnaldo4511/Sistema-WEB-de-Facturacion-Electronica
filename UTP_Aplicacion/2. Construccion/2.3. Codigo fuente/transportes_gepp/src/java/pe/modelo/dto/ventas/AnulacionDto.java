/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dto.ventas;

import java.util.Date;

/**
 *
 * @author octavio
 */
public class AnulacionDto {

    private long idDocumentoVenta;
    private Date fechaEmisionDocumentoVenta;
    private String tipo;
    private String motivo;

    public AnulacionDto() {
    }

    public void setIdDocumentoVenta(long idDocumentoVenta) {
        this.idDocumentoVenta = idDocumentoVenta;
    }

    public long getIdDocumentoVenta() {
        return this.idDocumentoVenta;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public Date getFechaEmisionDocumentoVenta() {
        return fechaEmisionDocumentoVenta;
    }

    public void setFechaEmisionDocumentoVenta(Date fechaEmisionDocumentoVenta) {
        this.fechaEmisionDocumentoVenta = fechaEmisionDocumentoVenta;
    }
    
}
