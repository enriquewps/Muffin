/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.pixel.service;

/**
 *
 * @author Enrique
 */
public interface UsuarioService {
    
    boolean registrado(String correo);
    
    boolean crearUsuario(String nombre, String usuario, String correo, String contrasena, 
            boolean adminsitrador);
    
    elim
}
