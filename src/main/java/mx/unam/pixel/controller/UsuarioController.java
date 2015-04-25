/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.pixel.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
       usuario = new Usuario();
        usuarios = this.usuarioService.findAll();
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    public boolean registraUsuario(){
        if(usuarios == null)usuarios = new ArrayList<Usuario>();
        try{
        
        for (Usuario u: usuarios){
            if(u.getCorreo().equals(usuario.getCorreo()) ||
                    u.getNombreUsuario().equals(usuario.getNombreUsuario()))
                return false;
        }
        boolean creado  =this.usuarioService.crearUsuario(usuario);
        this.usuario=new Usuario();
        this.usuario.setNombre("Se guardo");
        return creado;
        }catch(Exception e){
            this.usuario=new Usuario();
            this.usuario.setNombre("NO se guardo");
        return false;
        }
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
    
    public void iniciarSesion(){
        if (usuario == null)usuario = new Usuario();
        System.out.println(usuario.getNombre()+" nombre y passs"+usuario.getContrasena());
        usuario = usuarioService.iniciarSesion(usuario.getNombre(), usuario.getContrasena());
        if (usuario!= null){
            busqueda = "Sesion iniciada";
            
        }
        else{
            busqueda = "Sesion no iniciada";
            
        }
    }
    
        public void enviaMensaje(String mensaje){
        try {
            Properties props = new Properties();
            
            // Nombre del host de correo, es smtp.gmail.com
                props.setProperty("mail.smtp.host", "smtp.gmail.com");

                // TLS si está disponible
                props.setProperty("mail.smtp.starttls.enable", "true");

                // Puerto de gmail para envio de correos
                props.setProperty("mail.smtp.port","587");

                // Nombre del usuario
                props.setProperty("mail.smtp.user", "ejemplo@gmail.com");
                
                // Si requiere o no usuario y password para conectarse.
                props.setProperty("mail.smtp.auth", "true");
                
                // Preparamos la sesion
                Session session=Session.getDefaultInstance(props);
                //session.setDebug(true);
                
                //Construimos el mensage
                MimeMessage message=new MimeMessage(session);
                InternetAddress cuenta=new InternetAddress("enrique.wps@gmail.com");
                message.setFrom(); //Correo electronico que manda el mensaja
                message.addRecipient(Message.RecipientType.TO,new InternetAddress("vampa@ciencias.unam.mx"));
                message.setSubject("Test MUFFIN");
                message.setText("Test Muffin Cuerpo");
                // Abrimos la comunicacion 
                Transport t=session.getTransport("smtp");
                
                t.connect("dasds@gmail.com","asdsadas"); // Ususario y contraseña
                t.sendMessage(message,message.getAllRecipients());
                // Cierre
                t.close();
                
        } catch (AddressException ex) {
            System.err.println("er");
        } catch (MessagingException ex) {
            Logger.getLogger(MailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
