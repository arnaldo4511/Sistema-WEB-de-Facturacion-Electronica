/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.administracion;

import java.util.List;
import pe.modelo.pojo.CargaSesion;
/**
 *
 * @author octavio
 */
public interface ICargaSesionDao {

    public CargaSesion crear(long idUsuario);

}
