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
 *Esta clase sirve para agregar y eliminar usuarios y para controlar su relacion con los comentarios
 * cuando se borra un usuario desde aqui se eliminan tambien sus ocmentarios
 * se tiene tambien una instancia de todos los usuarios que hay  y una del que se esta 
 * registrando en caso de que se este usando desde registro
 * @author Enrique
 */
@Controller("usuarioController")
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
