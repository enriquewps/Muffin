/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.pixel.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import mx.unam.pixel.model.Categoria;
import mx.unam.pixel.model.Local;
import mx.unam.pixel.model.Usuario;
import mx.unam.pixel.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Enrique
 */
@Controller("localController")
@Scope("session")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    
    private String busqueda = "";
    
    private List<Usuario> usuarios;
    
    
    private Usuario usuario;
    
    @PostConstruct
    public void init(){
        usuarios = this.usuarioService.findAll();
    }
    
    
    public void registraUsuario(){
        this.usuarioService.crearUsuario(usuario);
        this.usuario=new Usuario();
    }
    
    public void borraUsuario(Usuario u){
        this.usuarioService.eliminaUsuario(u);
    }
    
    public void recuperaContraseña(String correo ){
        // Por Implementar
    }
    
    public void buscaPorNombre(){
        usuarios = usuarioService.findByNombre(busqueda);
    }

    
    public void buscaPorCorreo(){
        usuarios = usuarioService.findByCorreo(busqueda);
    }
    
    
    public void buscaPorNombreUsuario(){
        usuarios = usuarioService.findByNombreUsuario(busqueda);
    }
    
    
}
