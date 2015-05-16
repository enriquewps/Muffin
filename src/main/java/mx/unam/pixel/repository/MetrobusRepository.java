/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.pixel.repository;

import java.util.List;
import mx.unam.pixel.model.Metrobus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * *Consultas para el Metrobus 
 * @author PIXEL
 */
public interface MetrobusRepository extends CrudRepository<Metrobus, Integer>{
    
    @Query("SELECT l FROM Metrobus l")
    @Override
    List<Metrobus> findAll();

    @Query("SELECT loc FROM Metrobus loc WHERE loc.nombre LIKE CONCAT(?,'%') ")
    List<Metrobus> findByNombre(String nombre);
        
}
