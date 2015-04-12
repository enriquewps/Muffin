/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.pixel.service;

import java.util.List;
import mx.unam.pixel.model.Usuario;

/**
 * Interface para operar con el susuario aqui se crean unsiarios y se aliminan asi como administradores
 * @author Enrique
 */
public interface UsuarioService {
    
    
    boolean crearUsuario(Usuario usuario);
    
    void eliminaUsuario(Usuario usuario);
    
    List<Usuario> findAll();
    
    List<Usuario> findByNombre(String nombre);
    
    List<Usuario> findByCorreo(String correo);
    
    List<Usuario> findByNombreUsuario(String nombreUsuario);
    
    void creaAdministrador(Usuario usuario);
    
    void eliminaAdministrador(Usuario usuario);
    
    
}
