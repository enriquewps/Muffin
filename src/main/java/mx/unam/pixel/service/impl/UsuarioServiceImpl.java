/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.pixel.service.impl;

import java.util.ArrayList;
import java.util.List;
import mx.unam.pixel.model.Usuario;
import mx.unam.pixel.repository.ComentarioRepository;
import mx.unam.pixel.repository.UsuarioRepository;
import mx.unam.pixel.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Enrique
 */
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private ComentarioRepository comentarioRepository;
    
    
    @Override
    public boolean crearUsuario(Usuario usuario) {

        if (usuario.isAdministrador())usuario.setRol("ROL_ADMIN");
        else usuario.setRol("ROL_USER");
        usuarioRepository.save(usuario);
        return true;
    }

    @Override
    public void eliminaUsuario(Usuario usuario) {
        usuarioRepository.delete(usuario.getId());
        comentarioRepository.eliminaComentariosUsuario(usuario.getId());
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> findByNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }

    @Override
    public List<Usuario> findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    @Override
    public List<Usuario> findByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    @Override
    public void creaAdministrador(Usuario usuario) {
        usuarioRepository.rolAdministrador(usuario.getNombreUsuario(), true);
    }

    @Override
    public void eliminaAdministrador(Usuario usuario) {
        usuarioRepository.rolAdministrador(usuario.getNombreUsuario(), false);
    }

    @Override
    public Usuario iniciarSesion(String nombre, String contrasena) {
        ArrayList<Usuario> us = (ArrayList<Usuario>) usuarioRepository.findByNombreContrasena(nombre, contrasena);
        return (us.size() == 0 )? null:us.get(0);
    }

    @Override
    public void guardaUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }
    
    
    
}
