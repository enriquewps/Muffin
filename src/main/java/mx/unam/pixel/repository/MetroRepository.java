/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.pixel.repository;

import java.util.List;
import mx.unam.pixel.model.Metro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * *Consultas para el METRO
 * @author PIXEL
 */
public interface MetroRepository extends CrudRepository<Metro, Integer>{
    
    @Query("SELECT l FROM Metro l")
    @Override
    List<Metro> findAll();

    @Query("SELECT loc FROM Metro loc WHERE loc.nombre LIKE CONCAT(?,'%') ")
    List<Metro> findByNombre(String nombre);
    
}
