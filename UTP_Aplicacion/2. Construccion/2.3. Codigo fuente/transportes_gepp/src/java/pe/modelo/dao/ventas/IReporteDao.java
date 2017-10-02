/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.ventas;

import pe.modelo.dao.publico.*;
import java.util.List;
import net.sf.jasperreports.engine.JasperPrint;
import pe.modelo.dto.ventas.NotificacionDto;
import pe.modelo.dto.ventas.AnulacionDto;
import pe.modelo.dto.ParametroDto;
import pe.modelo.dto.ventas.ListaDocumentoVentaDto;
import pe.modelo.pojo.DocumentoVenta;
import pe.modelo.pojo.TipoDocumentoVenta;
import pe.modelo.pojo.vista.VwSelDocumentoVenta;

/**
 *
 * @author octavio
 */
public interface IReporteDao {

    public JasperPrint generarVentaXPlacaAnual(Integer anhio, Long idEmpresa);
    public JasperPrint generarConDocumentoVenta(Long idEmpresa,Integer anhio,Integer mes,Integer diaInicio,Integer diaFin,Integer inicioCorrelativo);

}
