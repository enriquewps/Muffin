/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.pixel.repository;

import java.util.List;
import mx.unam.pixel.model.Pumabus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * *Consultas para el catalogo 
 * @author Enrique
 */
public interface PumabusRepository extends CrudRepository<Pumabus, Integer>{
    
    @Query("SELECT l FROM Pumabus l")
    @Override
    List<Pumabus> findAll();

    @Query("SELECT loc FROM Pumabus loc WHERE loc.estacion LIKE CONCAT(?,'%') ")
    List<Pumabus> findByNombre(String nombre);
        
}
