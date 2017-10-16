/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dto.ventas;

import pe.modelo.pojo.DocumentoVenta;

/**
 *
 * @author octavio
 */
public class NotificacionDto {

    private long idDocumentoVenta;
    private DocumentoVenta documentoVenta;
    private String tipo;
    private String email;

    public NotificacionDto() {
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public DocumentoVenta getDocumentoVenta() {
        return documentoVenta;
    }

    public void setDocumentoVenta(DocumentoVenta documentoVenta) {
        this.documentoVenta = documentoVenta;
    }
}
