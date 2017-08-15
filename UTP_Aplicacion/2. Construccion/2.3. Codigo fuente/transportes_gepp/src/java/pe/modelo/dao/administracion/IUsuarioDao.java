/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.modelo.dao.administracion;

/**
 *
 * @author octavio
 */
public interface IUsuarioDao {
    public boolean ingresarSistema(String nombre,String clave);
    
}
