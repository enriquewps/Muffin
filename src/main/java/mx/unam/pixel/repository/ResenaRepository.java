/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.pixel.repository;

import java.util.Date;
import java.util.List;
import mx.unam.pixel.model.Local;
import mx.unam.pixel.model.Comentario;
import mx.unam.pixel.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Enrique
 */
public interface ResenaRepository extends CrudRepository<Comentario, Integer>{
     @Query("SELECT l FROM Resena l")
     @Override
    List<Comentario> findAll();
    
        @Query("SELECT r FROM Resena r JOIN FECTH r.usuario u "+
           "WHERE u.nombre LIKE CONCAT('%',?,'%')")
    List<Usuario> findByUsuario(String usuario);
    
        @Query("SELECT r FROM Resena r  "+
           "WHERE r.usuario.nombre LIKE CONCAT('%',?,'%')")
    List<Comentario> findByUsuario1(String usuario);
    
            @Query("SELECT r FROM Resena r JOIN FECTH r.local u "+
           "WHERE u.nombre LIKE CONCAT('%',?,'%')")
    List<Comentario> findByLocal(String local);
    
        @Query("SELECT r FROM Resena r  "+
           "WHERE r.local.nombre LIKE CONCAT('%',?,'%')")
    List<Comentario> findByLocal1(String local);
    
        @Query("SELECT l FROM Resena l WHERE l.fecha >= ?1 AND l.fecha<= ?2")
    List<Comentario> findByFecha(Date antes,Date despues);
    
    @Query("SELECT l FROM Resena l WHERE l.calificacion >= ?1 AND l.calificacion<= ?2")
    List<Comentario> findByCalificacion(Integer menor,Integer mayor);
    
}
