/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.pixel.service;

import java.util.ArrayList;
import java.util.List;
import mx.unam.pixel.model.Local;
import mx.unam.pixel.model.Pumabus;
import mx.unam.pixel.model.BiciPuma;
import mx.unam.pixel.model.Categoria;
import mx.unam.pixel.model.Comentario;
import mx.unam.pixel.model.Facultad;
import mx.unam.pixel.model.Metrobus;
import mx.unam.pixel.model.Metro;
import mx.unam.pixel.model.Usuario;

/**
 * Interface para operar con los locales, aqui se hacen busquedad, se agrega y se calcula la calificacion
 * @author PIXEL
 */
public interface LocalService {
    
    /**
     * Gusradara un local en la base de datos
     * @param local local a guardar
     */
    void guardaLocal(Local local);
    
    /**
     * Elimina un local a la base de datos
     * @param local local a eliminar
     */
    void eliminaLocal(Local local);
    
    /**
     * busca todos los locales en la base de datos
     * @return
     */
    List<Local> findAll();
        
    /**
     * encuentra todos los locales en la base de datos que cumplan con lo pedido por los parametros
     * @param nombre
     * @param rangoInferior
     * @param rangoSuperior
     * @param wifi
     * @param estacionamiento
     * @param facultad
     * @param pumabus
     * @param bicipuma
     * @param metrobus
     * @param bano
     * @param categoria
     * @return la lista con los locales que cumplen con los parametros
     */
    List<Local> busquedaAvanzada(String nombre,
            Integer rangoInferior, Integer rangoSuperior, Boolean wifi, Boolean estacionamiento,
            String facultad, String pumabus, String bicipuma,
            String metrobus, Boolean bano,String categoria);

    /**
     * Encuentra un local en base a un punto geografico
     * @param lat
     * @param lon
     * @return los locales en el punto
     */
    List<Local> findByPunto(Double lat,Double lon);
    
    /**
     * Encuentra los locales con un nombre que contengan la cadena especificada
     * @param nombre
     * @return locales que cumplan la condicion
     */
    List<Local> findByNombre(String nombre);

    /**
     *  Busca a todos los pumabuses
     * @return
     */
    List<Pumabus> findAllPumabus();

    /**
     * Busca todos los metrobuses
     * @return
     */
    List<Metrobus> findAllMetrobus();

    /**
     * Busca todos los metros
     * @return
     */
    List<Metro> findAllMetro();

    /**
     * Busca todoas las etaciones de bicipuma
     * @return
     */
    List<BiciPuma> findAllBiciPuma();
    
    /**
     * Busca todas las facultades
     * @return
     */
    List<Facultad> findAllFacultades();
        
    /**
     * Encuentra todos los comentarios de un local
     * @param id el id del local
     * @return los comentarios del local
     */
    List<Comentario> findComentarios(Integer id);
    
    /**
     * encuentra las esecialidades posibles
     * @return
     */
    List<Categoria> findEspecialidades();
    
    /**
     * Actualiza la cilifiacion de un local en base a sus comentarios
     * @param local
     */
    void actualizaCalificacion(Local local);
    
    /**
     * Guarda un categoria en la base de datos
     * @param c
     */
    void guardaCategoria(Categoria c);
    
    /**
     * Encuentra un local por su ID
     * @param id
     * @return
     */
    Local findById(Integer id);
    
    /**
     * Regresa todos los locales de la base de datos
     * @return
     */
    List<Local> findAllAdmin();

    /**
     * Regresa la foto de un local
     * @param id el id del local
     * @return la foto del local
     */
    public byte[] findFoto(Integer id);

    /**
     * Guarda un comentario en la base de datos
     * @param comentario
     */
    public void guardaComentario(Comentario comentario);

    /**
     * guarda una facultad en la base de datos
     * @param facultad
     */
    public void guardaFacultad(Facultad facultad);

    /**
     * Crea un local por primera vez en la base de datos
     * @param local
     */
    public void creaLocal(Local local);

    /**
     * Encuentra los usuarios en la base de datos
     * @return
     */
    public ArrayList<Usuario> findUsuarios();
    
    /**
     * Guarda un usuario en la base de datos
     * @param usuario
     */
    public void guardaUsuario(Usuario usuario);

    

}
