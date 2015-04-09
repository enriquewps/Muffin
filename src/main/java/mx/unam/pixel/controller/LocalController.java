/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.pixel.controller;

import java.util.List;

import mx.unam.pixel.model.Categoria;
import mx.unam.pixel.model.Facultad;

/**
 *
 * @author Enrique
 */
public class LocalController {
  private String nombre;
    private double latitud;
    
    private double longitud;
    
    
    //Preguntar a memo por el trigger
    private Integer rangoInferior;
     
    private Integer rangoSuperior;
     
    
    private boolean wifi;
    
    //Solo puede tener valores entre 1 y 3 significando 1: comer 2: llevar 3: ambos
    private int comerOLlevar;
    
    private boolean aprobado;
    
    private Facultad facultad;
    
    private Categoria recomendacion;
        
    private List<Categoria> categorias;

    private String descripcion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
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
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public Categoria getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(Categoria recomendacion) {
        this.recomendacion = recomendacion;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
