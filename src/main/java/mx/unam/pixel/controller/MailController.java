/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.pixel.controller;


import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.mail.Message;  // 
import javax.mail.MessagingException;
import javax.mail.Session; // entablar sesion 
import javax.mail.Transport; // envia mensajes al servidor 
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;  // direccion con la cual queremos entablar una conexion  
import javax.mail.internet.MimeMessage;  //
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
/**
 * 
 * @author PIXEL
 */

@Controller("mailController")
@Scope("session")
public class MailController {
    
      
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
    
    @PostConstruct
    public void init(){
        enviaMensaje("");
    }


    
}
