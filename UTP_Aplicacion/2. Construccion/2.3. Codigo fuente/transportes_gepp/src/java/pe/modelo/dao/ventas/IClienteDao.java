/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.ventas;

import pe.modelo.dao.publico.*;
import java.util.List;
import pe.modelo.dto.ParametroDto;
import pe.modelo.dto.ventas.ListaClientesDto;
import pe.modelo.pojo.Cliente;
import pe.modelo.pojo.vista.VwAutCliente;

/**
 *
 * @author octavio
 */
public interface IClienteDao {

    public void crear(Cliente cliente);

    public void editar(Cliente cliente);

    public Cliente buscar(long id);

    public void eliminar(long id);

    public ListaClientesDto listar(ParametroDto[] parametros);

    List<VwAutCliente> autocompletar(String criterio, String codigoTipoDocumentoEntidad);

}
