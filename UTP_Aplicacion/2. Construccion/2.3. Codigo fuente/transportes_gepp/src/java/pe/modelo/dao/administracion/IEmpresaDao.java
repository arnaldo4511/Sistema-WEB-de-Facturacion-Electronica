/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.administracion;

import java.util.List;
import pe.modelo.pojo.Empresa;

/**
 *
 * @author octavio
 */
public interface IEmpresaDao {

    public void crear(Empresa empresa);

    public void editar(Empresa empresa);

    public Empresa buscar(long id);

    public void eliminar(long id);

    public List<Empresa> listar();

}
