/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.pixel.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Enrique
 */
@Entity
public class Local implements Serializable {
   // private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Id
    private String nombre;
    @NotNull
    private double latitud;
    @NotNull
    private double longitud;
    @NotNull
    private Double rangoInferior;
     
    @NotNull
    private Double rangoSuperior;
     
    
    private boolean wifi;
    private boolean estacionamiento; 
    //Solo puede tener valores entre 1 y 3 significando 1: comer 2: llevar 3: ambos
    private int comerOLlevar;
    private boolean visible;
    
    @ManyToOne(cascade = CascadeType.ALL,fetch =FetchType.EAGER)
    private Facultad facultad;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Categoria recomendacion;
        
    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "local",
            orphanRemoval = true)
    private List<Categoria> categorias;
    
    
    
    public Integer getId() {
        return id;
    }
    
    

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Local)) {
            return false;
        }
        Local other = (Local) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.unam.pixel.model.Local[ id=" + id + " ]";
    }
    
}
