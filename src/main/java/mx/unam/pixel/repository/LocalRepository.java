/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.pixel.repository;

import java.util.List;
import mx.unam.pixel.model.Local;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Enrique
 */
public interface LocalRepository extends CrudRepository<Local, Integer>{
     @Query("SELECT l FROM Local l")
     @Override
    List<Local> findAll();
    
    @Query("SELECT loc FROM Local loc WHERE loc.nombre LIKE CONCAT(?,'%') ")
    List<Local> findByNombre(String nombre);
    

    
    @Query("SELECT l FROM Local l WHERE l.rangoInferior > ? AND l.rangoSuperior< ?")
    List<Local> findByRangoInferior(Integer rangoInferior,Integer rangoSuperior);

    @Query("SELECT loc FROM Local loc WHERE loc.wifi = true ")
    List<Local> findByWifi();

    @Query("SELECT loc FROM Local loc WHERE loc.estacionamiento = true ")
    List<Local> findByEstacionamiento();
    
    @Query("SELECT loc FROM Local loc WHERE loc.visible = true ")
    List<Local> findByVisible();

    @Query("SELECT loc FROM Local loc WHERE loc.comeOLlevar = 1 or loc.comeOLlevar = 3 ")
    List<Local> findByComer();
    
    @Query("SELECT loc FROM Local loc WHERE loc.comeOLlevar = 2 or loc.comeOLlevar = 3 ")
    List<Local> findByLlevar();    
    
    @Query("SELECT l FROM Local l JOIN FECTH l.facultad f "+
           "WHERE f.nombre LIKE CONCAT('%',?,'%')")
    List<Local> findByFacultad(String facultad);
    
    @Query("SELECT l FROM Local l JOIN FECTH l.recomendacion r "+
           "WHERE r.nombre LIKE CONCAT('%',?,'%')")
    List<Local> findByRecomendacion(String recomendacion);
    
        @Query("SELECT l FROM Local l  "+
           "WHERE l.recomendacion.nombre LIKE CONCAT('%',?,'%')")
    List<Local> findByRecomendacion1(String recomendacion);
        
    @Query("SELECT l FROM Local l JOIN FECTH l.categorias c "+
           "WHERE c.nombre LIKE CONCAT('%',?,'%') ")
    List<Local> findByCategoria(String categoria);
    
    @Query("SELECT l FROM Local l JOIN FECTH l.rutas r "+
           "WHERE r.nombreRuta LIKE CONCAT('%',?,'%')")
    List<Local> findByRutaPumaBus(String nombreRuta);
}
