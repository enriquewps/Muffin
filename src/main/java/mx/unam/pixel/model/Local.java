/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.pixel.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
//import sun.misc.BASE64Encoder;
import java.util.Base64.Encoder;
import javax.persistence.JoinColumn;
import mx.unam.pixel.repository.LocalRepository;
import mx.unam.pixel.service.LocalService;
import mx.unam.pixel.service.impl.LocalServiceImpl;



/**
 * *Clase de modelo
 * @author Enrique
 */
@Entity
public class Local implements Serializable {
   // private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull
    private String nombre;
    @NotNull
    private double latitud;
    @NotNull
    private double longitud;
    
    private Boolean bano;
    
    
    //Preguntar a memo por el trigger
    @NotNull
    private Integer rangoInferior;
     
    @NotNull
    private Integer rangoSuperior;
     
    
    private boolean wifi;
    
    
    //Solo puede tener valores entre 1 y 3 significando 1: comer 2: llevar 3: ambos
    private int comerOLlevar;
    
    private boolean aprobado;

    public Boolean getBano() {
        return bano;
    }

    public void setBano(Boolean bano) {
        this.bano = bano;
    }
    
    
    @ManyToOne(cascade = CascadeType.MERGE,fetch =FetchType.EAGER)
    @JoinColumn(name = "faciltad_id", referencedColumnName = "id")
    private Facultad facultad;
    
        @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)

    private Categoria recomendacion;
        
    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Categoria> categorias;

    private String descripcion;
    
    
    private Integer calificacion;
    
    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            mappedBy = "local",
            orphanRemoval = true)
    private List<Comentario> comentarios;    
    
    @Lob
    @Column(name = "FOTO",columnDefinition = "LONGBLOB") 
    private byte[] foto;

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        System.out.println(calificacion+"");
        this.calificacion = calificacion;
    }

    public List<Comentario> getComentarios() {
        if(comentarios == null)comentarios = new ArrayList<Comentario>();
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        //setDescripcion(descripcion);
        this.descripcion = descripcion;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        System.out.println(nombre);
        this.nombre = nombre;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        System.out.println(latitud+"");
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public Integer getRangoInferior() {
        return rangoInferior;
    }

    public void setRangoInferior(Integer rangoInferior) {
        this.rangoInferior = rangoInferior;
    }

    public Integer getRangoSuperior() {
        return rangoSuperior;
    }

    public void setRangoSuperior(Integer rangoSuperior) {
        this.rangoSuperior = rangoSuperior;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public int getComerOLlevar() {
        return comerOLlevar;
    }

    public void setComerOLlevar(int comerOLlevar) {
        this.comerOLlevar = comerOLlevar;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    
    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public Facultad getFacultad() {
        if (facultad == null)facultad = new Facultad();
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public Categoria getRecomendacion() {
        if (recomendacion == null)recomendacion =new Categoria();
        return recomendacion;
    }

    public void setRecomendacion(Categoria recomendacion) {
        System.out.println(recomendacion.getNombre());
        this.recomendacion = recomendacion;
    }

    public List<Categoria> getCategorias() {
        if (categorias == null )categorias = new ArrayList<>();
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
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

    public String getFotoUrl(){ 
            if(this!=null && this.getFoto()!=null){
                System.out.println("no es null la imagen");
                //BASE64.Encoder encoder = new BASE64Encoder();
                String imageString = Base64.getEncoder().encodeToString(this.getFoto());
                
                
                
                return   imageString;
            }
            else{
                                System.out.println("SI es null la imagen");
                return null;
            }
        }
    
}
