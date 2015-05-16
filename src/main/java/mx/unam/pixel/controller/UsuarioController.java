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
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;



//con ajax para eventos 
import javax.faces.application.FacesMessage;
/*import javax.faces.bean.ManagedBean;*/
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *Esta clase sirve para agregar y eliminar usuarios y para controlar su relacion con los comentarios
 * cuando se borra un usuario desde aqui se eliminan tambien sus ocmentarios
 * se tiene tambien una instancia de todos los usuarios que hay  y una del que se esta 
 * registrando en caso de que se este usando desde registro
 * @author PIXEL
 */
@Controller("usuarioController")
@Scope("session")
@ManagedBean
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    
    private String busqueda = "";
    
    private List<Usuario> usuarios;
    

    //@ManagedProperty(value="#{usuario}")
    private Usuario usuario = new Usuario();
    
    private Usuario usuarioRegistro = new Usuario();

    private String correoRecuperar ="";
    
    
    @Autowired
    private JavaMailSenderImpl mailSender;
    
    public Usuario getUsuarioRegistro() {
        if (usuarioRegistro == null)usuarioRegistro = new Usuario();
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(Usuario usarioRegistro) {
        this.usuarioRegistro = usarioRegistro;
    }

    public String getCorreoRecuperar() {
        return correoRecuperar;
    }

    public void setCorreoRecuperar(String correoRecuperar) {
        this.correoRecuperar = correoRecuperar;
    }
    
    @PostConstruct
    public void init(){
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
        
        return usuarioService.findAll();
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario() {
        if(usuario == null)
            usuario = new Usuario();
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuarioRegistro = usuario;
    }
    
    /**
     * Crea un usuario por primera vez en la base de datos
     * @return se creo o no el usuario
     */
    public boolean registraUsuario(){
        if(usuarios == null)
            usuarios = new ArrayList<Usuario>();
        
        try{
        
        for (Usuario u: usuarios){
            if(u.getCorreo().equals(usuarioRegistro.getCorreo()) ||
                    u.getNombreUsuario().equals(usuarioRegistro.getNombreUsuario()))
                return false;
        }
        boolean creado  =this.usuarioService.crearUsuario(usuarioRegistro);

        return creado;
        }catch(Exception e){
            this.usuarioRegistro=new Usuario();
            this.usuarioRegistro.setNombre("No se guardo");
        return false;
        }
    }
    
    /**
     * Elimina un usuario de la base de datos
     */
    public void borraUsuario(){
        
        usuarioService.eliminaUsuario(usuarioRegistro);
        usuarioRegistro = new Usuario();
        usuarios = usuarioService.findAll();
        
    }
    
    /**
     * guarda usuarios por primera vez en la base de datos
     */
    public void guardaUsuario(){

        this.usuarioService.guardaUsuario(usuarioRegistro);
        this.usuarios = usuarioService.findAll();
        this.usuarioRegistro = new Usuario();
    }
    
    /**
     * Acrtualiza la informacion de un usario, solo si es administrador o no
     */
    public void actualizaUsuario(){
        this.usuarioService.actualizaUsuario(usuarioRegistro);
        this.usuarios = usuarioService.findAll();
        this.usuarioRegistro = new Usuario();
    }

    /**
     * Busca el usuario de con el campo "correoRecuperar" y si esta en la base de datos le 
     * envia su contraseña por correo
     */
    public void recuperaContrasena(){
        List<Usuario> u = usuarioService.findByCorreo(correoRecuperar);
        if(u!=null || u.size() > 0 ){
            //Aqui se envia correo al usuario investigar si se regresaria null o la lista vacia
            
        SimpleMailMessage mail=new SimpleMailMessage();
        
        mail.setTo(u.get(0).getCorreo());
        mail.setFrom("pixel.is.pruebas@gmail.com");
        mail.setSubject("Muffin");
        mail.setCc("pixel_developer@gmail.com");
        mail.setText("Tu contraseña es :"+u.get(0).getContrasena());

        
        mailSender.send(mail);
         
        }
            return;
    }
    
    /**
     * busca usuarios en la base de datos y los guarda en la lista de usuarios del controlador
     */
    public void buscaUsuario(){
    usuarios = usuarioService.findByNombre(busqueda);
    usuarios.addAll(usuarioService.findByCorreo(busqueda));
    usuarios.addAll(usuarioService.findByNombreUsuario(busqueda));
    }
    
    
    
    //Boorar?
    public void iniciarSesion(){
        if (usuarioRegistro == null)usuarioRegistro = new Usuario();
        //System.out.println(usuario.getNombre()+" nombre y passs"+usuario.getContrasena());
        usuario = usuarioService.iniciarSesion(usuarioRegistro.getNombre(), usuarioRegistro.getContrasena());
        if (usuario == null){
            usuario = new Usuario();
            busqueda = "No esta registrado";
        }else{
            busqueda = "Sesion Iniciada";
        }
    }
    
    /**
     * Envia por mensaje un correo con la informacion solicitada, se usa para enviar 
     * por correo la contraseña
     * @param mensaje
     */
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
    
    /**
     * Elimina un usuario de la base de datos, el usurio que esta en usuarioRegistro
     * @return
     */
    public String eliminaUsuario(){
         this.usuarioService.eliminaUsuario(usuarioRegistro);
         this.usuarioRegistro = new Usuario();
         this.usuarios=usuarioService.findAll();
         this.usuario = new Usuario();
         return "Se elimino el usuario";
     }


    public void buttonAction(ActionEvent actionEvent) {
        for (Usuario u: usuarios){
                if(u.getCorreo().equals(usuarioRegistro.getCorreo()) ||
                    u.getNombreUsuario().equals(usuarioRegistro.getNombreUsuario())){
                usuarioService.eliminaUsuario(usuarioRegistro);
                this.usuarioRegistro = new Usuario();
                addMessage("Usuario eliminado");
                }
        }
    }
     
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    /**
     * Da a d ebaja a un usuario para que la proxima que quiera inciar sesion ya no le sea posible
     * @param username el usuario que solicita la baja
     */
    public void solicitaBaja(String username){
        usuarioRegistro = usuarioService.findByNombreUsuario(username).get(0);
                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        usuarioService.eliminaUsuario(usuarioRegistro);
        //usuarioService.eliminaUsuario(usuario);
        usuarioRegistro = new Usuario();
        usuarios = usuarioService.findAll();
    }
        
}
