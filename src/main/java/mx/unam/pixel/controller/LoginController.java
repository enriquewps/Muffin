/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.pixel.controller;

import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

/**
 *
 * @author guillermorojas
 */
@Controller("loginController")
@Scope("request")
public class LoginController {
    
    private String username;
    private String rol;
    private Boolean loged;
    
    @PostConstruct
    public void init(){
      Object user =SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ( !(auth instanceof AnonymousAuthenticationToken) && (user instanceof UserDetails) ) {
            this.username=((UserDetails)user).getUsername();
            if(auth.getAuthorities().size()>0){
                this.rol=auth.getAuthorities().iterator().next().getAuthority();
            }
            this.loged=true;
        }
        else if(user instanceof String){
            this.username= user.toString();
            this.loged=false;
     }
        
    }

    public String getUsername() {
        return username;
    }


    public Boolean getLoged() {
        return loged;
    }


    public String getRol() {
        return rol;
    }
   
    
}
