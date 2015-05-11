/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.pixel.repository;

import java.util.List;
import mx.unam.pixel.model.Facultad;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * *Consultas para el catalogo 
 * @author Enrique
 */
public interface FacultadRepository extends CrudRepository<Facultad, Integer>{
     @Query("SELECT l FROM Facultad l")
     @Override
    List<Facultad> findAll();
    
}
