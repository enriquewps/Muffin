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
    
    
    public boolean registraUsuario(){
        for (Usuario u: usuarios){
            if(u.getCorreo().equals(usuario.getCorreo()) ||
                    u.getNombreUsuario().equals(usuario.getNombreUsuario()))
                return false;
        }
        boolean creado  =this.usuarioService.crearUsuario(usuario);
        this.usuario=new Usuario();
        return creado;
    }
    
    public void borraUsuario(Usuario u){
        this.usuarioService.eliminaUsuario(u);
    }
    
    public void recuperaContrasena(String correo ){
        List<Usuario> u = usuarioService.findByCorreo(correo);
        if(u!=null || u.size() > 0 )
            //Aqui se envia correo al usuario investigar si se regresaria null o la lista vacia
            return;
    }
    
    public void buscaUsuario(){
    usuarios = usuarioService.findByNombre(busqueda);
    usuarios.addAll(usuarioService.findByCorreo(busqueda));
    usuarios.addAll(usuarioService.findByNombreUsuario(busqueda));
    }
    
    
    
    
    
}
