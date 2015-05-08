/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.pixel.repository;
import java.util.List;
import mx.unam.pixel.model.Comentario;
import mx.unam.pixel.model.Local;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * *Consultas para los comentarios
 * @author Enrique
 */
public interface ComentarioRepository extends CrudRepository<Comentario, Integer> {
    
    @Query("DELETE FROM Comentario c WHERE c.usuario.id = ? ")
    void eliminaComentariosUsuario(Integer id);
    
    @Query ("SELECT c FROM Comentario c WHERE c.local.id = ?")
    List<Comentario> findByLocalID(Integer id);
    
    
}
