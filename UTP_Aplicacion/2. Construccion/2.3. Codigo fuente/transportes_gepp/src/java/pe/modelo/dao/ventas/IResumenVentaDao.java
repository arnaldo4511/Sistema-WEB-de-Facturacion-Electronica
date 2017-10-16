/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.ventas;

import java.util.List;
import pe.modelo.pojo.Producto;
import pe.modelo.pojo.ResumenVentas;
import pe.modelo.pojo.ResumenVentasGrupoVenta;

import java.util.List;
import net.sf.jasperreports.engine.JasperPrint;
import pe.modelo.dto.ParametroDto;
import pe.modelo.dto.ventas.ListaResumenesVentasDto;
import pe.modelo.pojo.Producto;
import pe.modelo.pojo.ResumenVentas;
import pe.modelo.pojo.ResumenVentasGrupoVenta;
/**
 *
 * @author HP
 */
public interface IResumenVentaDao {

    public void crear(ResumenVentas resumenVentas);
    
    //public void editar(Producto producto);

    //public ResumenVentas buscar(long id);
    
    //public void enviarSunat(ResumenVentas resumenVentas);

    public void anular(long id);

    //public List<ResumenVentas> listar();
    
    //public List<String> autocomplete(String nombre);
    
    public ListaResumenesVentasDto listar(ParametroDto[] parametros);

    //public List<String> autocomplete(String nombre);
    public void enviarComunicacionBajaSunat(ResumenVentas resumenVentas);

    public void enviarResumenBoletasSunat(ResumenVentas resumenVentas);

    public ResumenVentas buscar(long id);

    public void consultarEstadoProceso(ResumenVentas resumenVentas);
    
    public JasperPrint generarReporte(ResumenVentas resumenVentas);
}
