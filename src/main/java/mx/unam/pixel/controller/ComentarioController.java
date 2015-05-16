/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.pixel.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import mx.unam.pixel.model.Comentario;
import mx.unam.pixel.model.Local;
import mx.unam.pixel.model.Usuario;
import mx.unam.pixel.repository.ComentarioRepository;
import mx.unam.pixel.repository.UsuarioRepository;
import mx.unam.pixel.service.LocalService;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
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
    
        @Autowired
    private UsuarioRepository usuarioRepository;
    
    
    //Local y usuario son los que estan relacionadoes en esta pantalla asi que 
    //se muestra el local que se selecciono y se usa el usuario que esta viendo la pantalla
    private Local local;
    
    private Usuario usuario;
    
    private Comentario comentario;
    
    private List<Comentario> comentarios;
    
        private MapModel simpleModel; // es usado en la vista del ver locales

    
    @PostConstruct
    public void init(){
        //comentarioRepository.deleteAll();
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
                simpleModel = new DefaultMapModel(); 

        LatLng coord = new LatLng(local.getLatitud(), local.getLongitud()); 
        simpleModel.addOverlay(new Marker(coord, local.getNombre()));

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
       local.setComentarios(new ArrayList<Comentario>());
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
    
    public void guardaComentario(String username){
        
        Comentario c = comentario;
        
                 comentario = new Comentario();
         comentario.setCalificacion(5);
         comentario.setComentario("");
        try{
        usuario = usuarioRepository.findByNombreUsuario(username).get(0);
            System.out.println(usuario.getNombre());
        }catch(Exception e){            System.out.println("error al buscar el usuario");
}
         c.setLocal(local);
         c.setFecha(new Date());
         c.setUsuario(usuario);
         
         local.getComentarios().add(c);
         //localService.guardaLocal(local);
         
         //comentarioRepository.save(local.getComentarios());
         //localService.actualizaCalificacion(local);

         localService.actualizaCalificacion(local);
                  localService.guardaComentario(c);
         local = localService.findById(local.getId());
         comentarios = local.getComentarios();

         local.setComentarios(comentarios);

         
        
    } 
    
    public void eliminiaComentario(Comentario c){
        comentarioRepository.delete(c);
        localService.actualizaCalificacion(local);        
    }

    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }

    public void setSimpleModel(MapModel simpleModel) {
        this.simpleModel = simpleModel;
    }
    
 
            
}
