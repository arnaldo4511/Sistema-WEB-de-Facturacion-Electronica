/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dto.ventas;

import java.util.List;
import pe.modelo.pojo.vista.VwSelDocumentoVenta;

/**
 *
 * @author octavio
 */
public class ListaDocumentoVentaDto {

    private List<VwSelDocumentoVenta> documentoVentas;
    private double nroDocumentoVentas;
    private double totalFacturas;
    private double totalBoletas;
    private double totalNotaCreditos;
    private double totalNotaDebitos;
    private double totalPendienteEnvioSunats;
    private double totalPendienteComunicacionBajas;

    public ListaDocumentoVentaDto(List<VwSelDocumentoVenta> documentoVentas, double nroDocumentoVentas, double totalFacturas, double totalBoletas, double totalNotaCreditos, double totalNotaDebitos, double totalPendienteEnvioSunats, double totalPendienteComunicacionBajas) {
        this.documentoVentas = documentoVentas;
        this.nroDocumentoVentas = nroDocumentoVentas;
        this.totalFacturas = totalFacturas;
        this.totalBoletas = totalBoletas;
        this.totalNotaCreditos = totalNotaCreditos;
        this.totalNotaDebitos = totalNotaDebitos;
        this.totalPendienteEnvioSunats = totalPendienteEnvioSunats;
        this.totalPendienteComunicacionBajas = totalPendienteComunicacionBajas;
    }

    public ListaDocumentoVentaDto() {
    }

    public List<VwSelDocumentoVenta> getDocumentoVentas() {
        return documentoVentas;
    }

    public void setDocumentoVentas(List<VwSelDocumentoVenta> documentoVentas) {
        this.documentoVentas = documentoVentas;
    }

    public double getNroDocumentoVentas() {
        return nroDocumentoVentas;
    }

    public void setNroDocumentoVentas(double nroDocumentoVentas) {
        this.nroDocumentoVentas = nroDocumentoVentas;
    }

    public double getTotalFacturas() {
        return totalFacturas;
    }

    public void setTotalFacturas(double totalFacturas) {
        this.totalFacturas = totalFacturas;
    }

    public double getTotalBoletas() {
        return totalBoletas;
    }

    public void setTotalBoletas(double totalBoletas) {
        this.totalBoletas = totalBoletas;
    }

    public double getTotalNotaCreditos() {
        return totalNotaCreditos;
    }

    public void setTotalNotaCreditos(double totalNotaCreditos) {
        this.totalNotaCreditos = totalNotaCreditos;
    }

    public double getTotalNotaDebitos() {
        return totalNotaDebitos;
    }

    public void setTotalNotaDebitos(double totalNotaDebitos) {
        this.totalNotaDebitos = totalNotaDebitos;
    }

    public double getTotalPendienteEnvioSunats() {
        return totalPendienteEnvioSunats;
    }

    public void setTotalPendienteEnvioSunats(double totalPendienteEnvioSunats) {
        this.totalPendienteEnvioSunats = totalPendienteEnvioSunats;
    }

    public double getTotalPendienteComunicacionBajas() {
        return totalPendienteComunicacionBajas;
    }

    public void setTotalPendienteComunicacionBajas(double totalPendienteComunicacionBajas) {
        this.totalPendienteComunicacionBajas = totalPendienteComunicacionBajas;
    }

}
