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
import pe.modelo.dto.ventas.Parametro;
import pe.modelo.pojo.DocumentoVenta;
import pe.modelo.pojo.TipoDocumentoVenta;

/**
 *
 * @author octavio
 */
public interface IDocumentoVentaDao {

    public void crear(DocumentoVenta documentoVenta);

    public void editar(DocumentoVenta documentoVenta);

    public DocumentoVenta buscar(long id);

    public JasperPrint generarReporte(DocumentoVenta documentoVenta);

    public void enviarCliente(NotificacionDto notificacionDto);

    public void eliminar(long id);

    public List<DocumentoVenta> listar(Parametro[] parametros);

    public long totalDocumentoVentas(Parametro[] parametros);

    public List<DocumentoVenta> autocompletar(String criterio);

}
