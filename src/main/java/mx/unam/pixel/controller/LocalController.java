/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.pixel.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import mx.unam.pixel.model.BiciPuma;

import mx.unam.pixel.model.Categoria;
import mx.unam.pixel.model.Facultad;
import mx.unam.pixel.model.Local;
import mx.unam.pixel.model.Metro;
import mx.unam.pixel.model.Metrobus;
import mx.unam.pixel.model.Pumabus;
import mx.unam.pixel.service.LocalService;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.map.Circle;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Esta clase controlla a los locales tiene una instancia de los locales en la base de dato sy ademas tiene 
 * un local que es el que se esta creando para egistrarlo en la base de datos
 * todos los demas atributos que estan en esta clase son para realizar busquedas, 
 * @author Enrique
 */
@Controller("localController")
@Scope("session")
public class LocalController {
        @Autowired
    private LocalService localService;
        
    
    private Local local;
    private List<Local> locales;
    
    private List<Pumabus> pumabuses;
    private List<Metro> metros;
    private List<BiciPuma> biciPumas;
    private List<Metrobus> metrobuses;
    private MapModel simpleModel; // es usado en la vista del ver locales
   
    private List<Facultad> facultades;

    
    private Boolean aprobado = true;
    private String nombre = "";
    private Categoria categoria;
    private Integer rangoInferior = 20;
    private Integer rangoSuperior = 200;
    private Boolean wifi = false;
    private Boolean estacionamiento = false;
    private Integer comeOLleva = 3;
    private String busquedaFacultad = "";
    private String pumabus = "";
    private String bicipuma = "";
    private String metro = "";
    private String metrobus = "";
    private Boolean admin = false;
    private String facultad;
    
    @PostConstruct
    public void init(){
 
        
        //se va a borrar pues estan en las facultades
        pumabuses=this.localService.findAllPumabus();
        metros=this.localService.findAllMetro();
        biciPumas=this.localService.findAllBiciPuma();
        metrobuses=this.localService.findAllMetrobus();
        //se va a borrar pues estan en las facultades        
        locales=this.localService.findAll();
       
        facultades = localService.findAllFacultades();
        
        simpleModel = new DefaultMapModel(); 
        
        for(Local l:this.locales){
            LatLng coord = new LatLng(l.getLatitud(), l.getLongitud()); 
            simpleModel.addOverlay(new Marker(coord, l.getNombre()));
        }
    }
    
    public void guardarCategoria(){
        this.local.getCategorias().add(categoria);
        this.categoria=new Categoria();   
    }
    
    public void guardarLocal(){
        LatLng coord = new LatLng(local.getLatitud(), local.getLongitud()); 
        simpleModel.addOverlay(new Marker(coord, local.getNombre()));

               
        this.localService.guardaLocal(local);
        this.locales=localService.findAll();
    /***************************************/
        this.local=new Local();
        this.local.setCategorias(new ArrayList<Categoria>());
    }
    
    
    public void seleccion(PointSelectEvent event){
         LatLng latlng = event.getLatLng();
         this.local.setLatitud(latlng.getLat());
         this.local.setLongitud(latlng.getLng());
    }

    
    public void buscarPorNombre(){
        this.locales=localService.findByNombre(nombre);
         simpleModel = new DefaultMapModel(); 
        for(Local l:this.locales){
            LatLng coord = new LatLng(l.getLatitud(), l.getLongitud()); 
            simpleModel.addOverlay(new Marker(coord, l.getNombre()));
        }
    }
    
    
     public void busquedaAvanzada(){
         
        this.locales = localService.busquedaAvanzada(aprobado, nombre, 
                metro, rangoInferior, rangoSuperior, wifi, estacionamiento,
                comeOLleva, bicipuma, pumabus, bicipuma, metro, metrobus, admin);
         
         
        simpleModel = new DefaultMapModel(); 
        for(Local l:this.locales){
            LatLng coord = new LatLng(l.getLatitud(), l.getLongitud()); 
            simpleModel.addOverlay(new Marker(coord, l.getNombre()));
        }
    }
    
    public void busquedaPunto(PointSelectEvent event){
        simpleModel = new DefaultMapModel(); 
        LatLng latlng = event.getLatLng();

        Circle circle = new Circle(latlng, 1000);
        circle.setStrokeColor("#00003c");
        circle.setFillColor("#00003c");
        circle.setFillOpacity(0.2);
         
        simpleModel.addOverlay(circle);
        
         this.locales=localService.findByPunto(latlng.getLat(),latlng.getLng());
       
        for(Local l:this.locales){
            LatLng coord = new LatLng(l.getLatitud(), l.getLongitud()); 
            simpleModel.addOverlay(new Marker(coord, l.getNombre()));
        }
    }
    
    public void borraLocal(Local loc){
         localService.eliminaLocal(loc);
         this.locales=localService.findAll();
          simpleModel = new DefaultMapModel(); 
        for(Local l:this.locales){
            LatLng coord = new LatLng(l.getLatitud(), l.getLongitud()); 
            simpleModel.addOverlay(new Marker(coord, l.getNombre()));
        }
     }
    
    
    public void handleFileUpload(FileUploadEvent event) {
            UploadedFile file=event.getFile();
            this.local.setFoto( file.getContents());   
    }
     
    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }



    public LocalService getLocalService() {
        return localService;
    }

    public void setLocalService(LocalService localService) {
        this.localService = localService;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public List<Local> getLocales() {
        return locales;
    }

    public void setLocales(List<Local> locales) {
        this.locales = locales;
    }

    public List<Pumabus> getPumabuses() {
        return pumabuses;
    }

    public void setPumabuses(List<Pumabus> pumabuses) {
        this.pumabuses = pumabuses;
    }
    
    

    public List<Metro> getMetros() {
        return metros;
    }

    public void setMetros(List<Metro> metros) {
        this.metros = metros;
    }

    public List<BiciPuma> getBiciPumas() {
        return biciPumas;
    }

    public void setBiciPumas(List<BiciPuma> biciPumas) {
        this.biciPumas = biciPumas;
    }

    public List<Metrobus> getMetrobuses() {
        return metrobuses;
    }

    public void setMetrobuses(List<Metrobus> metrobuses) {
        this.metrobuses = metrobuses;
    }

    public MapModel getSimpleModel() {
        return simpleModel;
    }

    public void setSimpleModel(MapModel simpleModel) {
        this.simpleModel = simpleModel;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public Boolean getEstacionamiento() {
        return estacionamiento;
    }

    public void setEstacionamiento(Boolean estacionamiento) {
        this.estacionamiento = estacionamiento;
    }

    public Integer getComeOLleva() {
        return comeOLleva;
    }

    public void setComeOLleva(Integer comeOLleva) {
        this.comeOLleva = comeOLleva;
    }

    public String getBusquedaFacultad() {
        return busquedaFacultad;
    }

    public void setBusquedaFacultad(String busquedaFacultad) {
        this.busquedaFacultad = busquedaFacultad;
    }

    public String getPumabus() {
        return pumabus;
    }

    public void setPumabus(String pumabus) {
        this.pumabus = pumabus;
    }

    public String getBicipuma() {
        return bicipuma;
    }

    public void setBicipuma(String bicipuma) {
        this.bicipuma = bicipuma;
    }

    public String getMetro() {
        return metro;
    }

    public void setMetro(String metro) {
        this.metro = metro;
    }

    public String getMetrobus() {
        return metrobus;
    }

    public void setMetrobus(String metrobus) {
        this.metrobus = metrobus;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
    
    
}