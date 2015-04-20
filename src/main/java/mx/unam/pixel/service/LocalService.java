/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.pixel.service;

import java.util.List;
import mx.unam.pixel.model.Local;
import mx.unam.pixel.model.Pumabus;
import mx.unam.pixel.model.BiciPuma;
import mx.unam.pixel.model.Comentario;
import mx.unam.pixel.model.Facultad;
import mx.unam.pixel.model.Metrobus;
import mx.unam.pixel.model.Metro;

/**
 * Interface para operar con los locales, aqui se hacen busquedad, se agrega y se calcula la calificacion
 * @author Miguel
 */
public interface LocalService {
    
    void guardaLocal(Local local);
    
    void eliminaLocal(Local local);
    
    List<Local> findAll();
        
    List<Local> busquedaAvanzada(Boolean aprobado, String nombre, String categoria,
            Integer rangoInferior, Integer rangoSuperior, Boolean wifi, Boolean estacionamiento,
            Integer comeOLleva, String facultad, String pumabus, String bicipuma, String metro,
            String metrobus, Boolean admin);

    
    List<Local> findByPunto(Double lat,Double lon);
    
    List<Local> findByNombre(String nombre);

    List<Pumabus> findAllPumabus();

    List<Metrobus> findAllMetrobus();

    List<Metro> findAllMetro();

    List<BiciPuma> findAllBiciPuma();
    
        List<Facultad> findAllFacultades();
        
    List<Comentario> findComentarios(String id);
    
    
    void actualizaCalificacion(Local local);

}