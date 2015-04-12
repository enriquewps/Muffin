/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.pixel.repository;

import java.util.List;
import mx.unam.pixel.model.Categoria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * *Consultas para las categorias
 * @author Enrique
 */
public interface CategoriaRepository extends CrudRepository<Categoria, Integer>{
     @Query("SELECT l FROM Categoria l")
     @Override
    List<Categoria> findAll();
    
    @Query("SELECT loc FROM Categoria loc WHERE loc.nombre LIKE CONCAT(?,'%') ")
    List<Categoria> findByNombre(String nombre);
     
    
    
    @Query("SELECT l FROM Categoria l WHERE l.precioInferior > ? AND l.precioSuperior< ?")
    List<Categoria> findByPrecioInferior(Integer precioInferior,Integer precioSuperior);
}
