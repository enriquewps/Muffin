/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.pixel.model;

import java.io.Serializable;
import java.util.ArrayList;
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
import mx.unam.pixel.repository.BiciPumaRepository;

/**
 * *Clase de modelo
 * @author Enrique
 */
@Entity
public class Facultad implements Serializable {
    //private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    //@Id    
    @NotNull
    private String nombreFac;
        
     
    
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Pumabus> pumabus;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<BiciPuma> biciPuma;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Metro> metro;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Metrobus> metroBus;

    //1:Abierto 2: estudiantes 3:Academico 0:no hay
    private Integer estacionamiento; 

    public Integer getEstacionamiento() {
        return estacionamiento;
    }

    public void setEstacionamiento(Integer estacionamiento) {
        this.estacionamiento = estacionamiento;
    }
    
    public String getNombreFac() {
        return nombreFac;
    }

    public void setNombreFac(String nombre) {
        this.nombreFac = nombre;
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
/*
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
*/
    @Override
    public String toString() {
        return "mx.unam.pixel.model.Facultad[ id=" + id + " ]";
    }
 
    public String getParadasBicipuma(){
        String pb="";
        if (biciPuma == null) biciPuma = new ArrayList<BiciPuma>();
        for (BiciPuma bici : biciPuma)
            pb+=bici.getName()+" - ";
        return pb.equals("")? "Sin estaciones":pb;
    }
    
    public String getParadasPumabus(){
        String pb="";
                if (pumabus == null) pumabus = new ArrayList<Pumabus>();
        for (Pumabus bici : pumabus)
            pb+=bici.getEstacion()+" - ";
        return pb.equals("")? "Sin estaciones":pb;
    }
    
    public String getParadasMetro(){
        String pb="";
                if (metro == null) metro = new ArrayList<Metro>();
        for (Metro bici : metro)
            pb+=bici.getNombre()+" - ";
        return pb.equals("")? "Sin estaciones":pb;
    }
    
    public String getParadasMetrobus(){
        String pb="";
                if (metroBus == null)metroBus = new ArrayList<Metrobus>();
        for (Metrobus bici : metroBus)
            pb+=bici.getNombre()+" - ";
        return pb.equals("")? "Sin estaciones":pb;
    }

    public String getTipoEstacionamiento(){
        String est = "";
        switch (estacionamiento){
            case 0:
                return "Sin Estacionamiento   ";
            case 1: return "Abierto   ";
            case 2: return "Estudiantes   ";
            case 4: return "Academicos y Estudiantes";
                default:return "Sin Estacionamiento";
        }
    }
    
    
}
