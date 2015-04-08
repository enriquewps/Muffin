/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.pixel.service;

import java.util.List;
import mx.unam.pixel.model.Usuario;

/**
 *
 * @author Enrique
 */
public interface UsuarioService {
    
    boolean estaRegistrado(String correo);
    
    boolean crearUsuario(Usuario usuario);
    
    boolean eliminaUsuario(Usuario usuario);
    
    List<Usuario> findAll();
    
    List<Usuario> findByNombre(String nombre);
    
    List<Usuario> findByCorreo(String correo);
    
    List<Usuario> findByNombreUsuario(String nombreUsuario);
    
    void creaAdministrador(Usuario usuario);
    
    
}
