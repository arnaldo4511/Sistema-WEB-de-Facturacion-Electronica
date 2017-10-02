/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dto.ventas;

import java.util.List;
import pe.modelo.pojo.vista.VwSelResumenVentas;

/**
 *
 * @author octavio
 */
public class ListaResumenesVentasDto {

    private List<VwSelResumenVentas> VwSelResumenesVentas;
    private double nroResumenesVentas;

    public ListaResumenesVentasDto() {
    }

    public ListaResumenesVentasDto(List<VwSelResumenVentas> VwSelResumenesVentas, double nroResumenesVentas) {
        this.VwSelResumenesVentas = VwSelResumenesVentas;
        this.nroResumenesVentas = nroResumenesVentas;
    }

    public List<VwSelResumenVentas> getVwSelResumenesVentas() {
        return VwSelResumenesVentas;
    }

    public void setVwSelResumenesVentas(List<VwSelResumenVentas> VwSelResumenesVentas) {
        this.VwSelResumenesVentas = VwSelResumenesVentas;
    }

    public double getNroResumenesVentas() {
        return nroResumenesVentas;
    }

    public void setNroResumenesVentas(double nroResumenesVentas) {
        this.nroResumenesVentas = nroResumenesVentas;
    }

}
