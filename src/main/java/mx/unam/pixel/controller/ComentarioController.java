/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.pixel.controller;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import mx.unam.pixel.model.Comentario;
import mx.unam.pixel.model.Local;
import mx.unam.pixel.model.Usuario;
import mx.unam.pixel.repository.ComentarioRepository;
import mx.unam.pixel.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *La clase que se encarga de las operaciones de los comentairos tales como añadir, eliminar y conseguir 
 * los comentarios que ya estan en la base datos, falta preguntarle a memo como recibimos el local y el usuario des
 * la pagina
 * @author Enrique
 */

@Controller("comentarioController")
@Scope("session")
public class ComentarioController {
    @Autowired
    private LocalService localService;
    
    
    @Autowired
    private ComentarioRepository comentarioRepository;
    
    
    //Local y usuario son los que estan relacionadoes en esta pantalla asi que 
    //se muestra el local que se selecciono y se usa el usuario que esta viendo la pantalla
    private Local local;
    
    private Usuario usuario;
    
    private Comentario comentario;
    
    private List<Comentario> comentarios;
    
    @PostConstruct
    public void init(){
        comentarios = comentarioRepository.findByLocalID(local.getId());
 
    }
    
    public void obtenComentarios(){
        comentarios = local.getComentarios();
    }
    
    public void guardaComentario(){
        
        comentario.setLocal(local);
        comentario.setFecha(new Date());
        comentario.setUsuario(usuario);
        comentarioRepository.save(comentario);
        
        local.getComentarios().add(comentario);
        localService.actualizaCalificacion(local);
    } 
    
    public void eliminiaComentario(Comentario c){
        comentarioRepository.delete(c);
        localService.actualizaCalificacion(local);        
    }
            
}
