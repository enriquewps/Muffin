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
        if (local != null)
        comentarios = comentarioRepository.findByLocalID(local.getId());
 
    }

    public LocalService getLocalService() {
        return localService;
    }

    public void setLocalService(LocalService localService) {
        this.localService = localService;
    }

    public ComentarioRepository getComentarioRepository() {
        return comentarioRepository;
    }

    public void setComentarioRepository(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
                comentarios = comentarioRepository.findByLocalID(local.getId());

    }
    
        public void setLocalUsuario(Local local,Usuario us) {
        this.local = local;
        this.usuario = us;
                comentarios = comentarioRepository.findByLocalID(local.getId());

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Comentario getComentario() {
        if (comentario == null)comentario= new Comentario();
        return comentario;
    }

    public void setComentario(Comentario comentario) {

        this.comentario = comentario;
    }

    public List<Comentario> getComentarios() {
        //local = localService.findById(local.getId());
        comentarios = localService.findComentarios(local.getId());
                //local.getComentarios();
        local.setComentarios(comentarios);
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
    
    
    
    public void obtenComentarios(){
       // local = localService.findById(local.getId());
       // comentarios = local.getComentarios();
      //          comentarios = localService.findComentarios(local.getId());
        //        local.setComentarios(comentarios);

    }
    
    public void guardaComentario(){
       /* 
        comentario.setLocal(local);
        comentario.setFecha(new Date());
        comentario.setUsuario(usuario);
        
        local.getComentarios().add(comentario);
        localService.guardaLocal(local);
        localService.actualizaCalificacion(local);
        local = localService.findById(local.getId());
        comentario = new Comentario();
        comentario.setCalificacion(5);
        comentario.setComentario("");*/
        //obtenComentarios();
        
        
        
         comentario.setLocal(local);
         comentario.setFecha(new Date());
         comentario.setUsuario(usuario);
         
         local.getComentarios().add(comentario);
         localService.guardaLocal(local);
         localService.actualizaCalificacion(local);
         local = localService.findById(local.getId());
         comentario = new Comentario();
         comentario.setCalificacion(5);
         comentario.setComentario("");
        
    } 
    
    public void eliminiaComentario(Comentario c){
        comentarioRepository.delete(c);
        localService.actualizaCalificacion(local);        
    }
            
}
