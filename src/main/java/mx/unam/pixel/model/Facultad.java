/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.pixel.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Enrique
 */
@Entity
public class Facultad implements Serializable {
    //private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
        @NotNull
    private String nombre;
    

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Pumabus> pumabus;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<BiciPuma> biciPuma;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Metro> metro;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Metrobus> metroBus;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Pumabus> getPumabus() {
        return pumabus;
    }

    public void setPumabus(List<Pumabus> pumabus) {
        this.pumabus = pumabus;
    }

    public List<BiciPuma> getBiciPuma() {
        return biciPuma;
    }

    public void setBiciPuma(List<BiciPuma> biciPuma) {
        this.biciPuma = biciPuma;
    }

    public List<Metro> getMetro() {
        return metro;
    }

    public void setMetro(List<Metro> metro) {
        this.metro = metro;
    }

    public List<Metrobus> getMetroBus() {
        return metroBus;
    }

    public void setMetroBus(List<Metrobus> metroBus) {
        this.metroBus = metroBus;
    }
    
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
        if (!(object instanceof Facultad)) {
            return false;
        }
        Facultad other = (Facultad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.unam.pixel.model.Facultad[ id=" + id + " ]";
    }
    
}
