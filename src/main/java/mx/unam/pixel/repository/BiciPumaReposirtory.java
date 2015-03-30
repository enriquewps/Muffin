/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.pixel.repository;

import java.util.List;
import mx.unam.pixel.model.BiciPuma;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Enrique
 */
public interface BiciPumaReposirtory extends CrudRepository<BiciPuma, Integer>{
    
    @Query("SELECT l FROM BiciPuma l")
    @Override
    List<BiciPuma> findAll();

    @Query("SELECT loc FROM BiciPuma loc WHERE loc.nombre LIKE CONCAT(?,'%') ")
    List<BiciPuma> findByNombre(String nombre);
    

}
