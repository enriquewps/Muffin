/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.pixel.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import mx.unam.pixel.model.BiciPuma;
import mx.unam.pixel.model.Categoria;
import mx.unam.pixel.model.Comentario;
import mx.unam.pixel.model.Facultad;
import mx.unam.pixel.repository.LocalRepository;
import mx.unam.pixel.model.Local;
import mx.unam.pixel.model.Metro;
import mx.unam.pixel.model.Metrobus;
import mx.unam.pixel.model.Pumabus;
import mx.unam.pixel.model.Usuario;
import mx.unam.pixel.repository.BiciPumaRepository;
import mx.unam.pixel.repository.CategoriaRepository;
import mx.unam.pixel.repository.ComentarioRepository;
import mx.unam.pixel.repository.FacultadRepository;
import mx.unam.pixel.repository.MetroRepository;
import mx.unam.pixel.repository.MetrobusRepository;
import mx.unam.pixel.repository.PumabusRepository;
import mx.unam.pixel.repository.UsuarioRepository;
import mx.unam.pixel.service.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


/**
 *
 * @author Miguel
 */
public class LocalServiceImpl implements LocalService{

    @Autowired
    private LocalRepository localRepository;
    
        @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private FacultadRepository facultadRepository;

    
        //Hayq eu quitar por que estan en la faculad
    @Autowired
    private PumabusRepository pumabusRepository;

    @Autowired
    private MetrobusRepository metrobusRepository;
    
    @Autowired
    private MetroRepository metroRepository;

    @Autowired
    private BiciPumaRepository bicipumaRepository;
    
    @Autowired
    private ComentarioRepository comentarioRepository;
    
        @Autowired
    private UsuarioRepository usuarioRepository;
    
    //Hayq eu quitar por que estan en la faculad
    
    @Override
    public void guardaLocal(Local local) {
       
        for(Categoria cat:local.getCategorias()){
            cat.setLocal(local);
            //categoriaRepository.save(cat);
        }
        if (local.getRecomendacion()!= null)local.getRecomendacion().setLocal(local);
        for(Comentario coment:local.getComentarios()){
            coment.setLocal(local);
            //usuarioRepository.save(coment.getUsuario());
        }
        
        
        int precio= 100000;
        for (Categoria cat: local.getCategorias()){
            if (precio > cat.getPrecioMenor())precio = cat.getPrecioMenor();
        }
        local.setRangoInferior(precio);
        
        precio= 0;
        for (Categoria cat: local.getCategorias()){
            if (precio < cat.getPrecioMayor())precio = cat.getPrecioMayor();
        }
        local.setRangoSuperior(precio);

       // local.setAprobado(true);
        //localRepository.save(local);
        
                try{
        local.setCalificacion(localRepository.getPromedio(local.getNombre()).intValue());
        }catch(Exception e){
       /* localRepository.save(l);
                l.setCalificacion(localRepository.getPromedio(l.getNombre()).intValue());
        localRepository.save(l);
*/
        }
        try{
            //facultadRepository.save(local.getFacultad());
        }catch(Exception e){}        
                
        localRepository.save(local);
                    //facultadRepository.save(local.getFacultad());

    }

    
    
    
    @Override
    public void eliminaLocal(Local local) {
        localRepository.delete(local);
    }

    @Override
    public List<Local> findAll() {
        return localRepository.findByAprobado(true);
    }

    
    
    @Override
    public List<Local> busquedaAvanzada(String nombre,
            Integer rangoInferior, Integer rangoSuperior, Boolean wifi, Boolean estacionamiento,
            String facultad, String pumabus, String bicipuma,
            String metrobus, Boolean admin, Boolean bano,String categoria){
        
        List<Local> resultado;

        if(admin){
            resultado = localRepository.findAll();
            if(nombre != "")
                resultado = intersecta(resultado,localRepository.findByNombreAdmin(nombre));
            
            resultado = intersecta(resultado,localRepository.findByBanoAdmin(bano));
            if(categoria != "")
                resultado = intersecta(resultado,localRepository.findByCategoriaAdmin(categoria));
            resultado = intersecta(resultado,localRepository.findByRangoInferiorAdmin(rangoInferior, rangoSuperior));
            if(wifi)
                resultado = intersecta(resultado,localRepository.findByWifiAdmin());
            if(estacionamiento)
                resultado = intersecta(resultado,localRepository.findByEstacionamientoAdmin());
            if(facultad != "")
                resultado = intersecta(resultado,localRepository.findByFacultadAdmin(facultad));
            if(pumabus != "")
                resultado = intersecta(resultado,localRepository.findByPumabusAdmin(pumabus));
            if(bicipuma != "")
                resultado = intersecta(resultado,localRepository.findByBiciPumaAdmin(bicipuma));
            if(metrobus != "")
                resultado = intersecta(resultado,localRepository.findByBiciPumaAdmin(metrobus));
            return resultado;
        }else{
            resultado = localRepository.findByAprobado(true);
            if(nombre != "")
                resultado = intersecta(resultado,localRepository.findByNombre(nombre));
            resultado = intersecta(resultado,localRepository.findByBano(bano));
            if(categoria != "")
                resultado = intersecta(resultado,localRepository.findByCategoria(categoria));
            resultado = intersecta(resultado,localRepository.findByRangoInferior(rangoInferior, rangoSuperior));
            if(wifi)
                resultado = intersecta(resultado,localRepository.findByWifi());
            if(estacionamiento)
                resultado = intersecta(resultado,localRepository.findByEstacionamiento());
            if(facultad != "")
                resultado = intersecta(resultado,localRepository.findByFacultad(facultad));
            if(pumabus != "")
                resultado = intersecta(resultado,localRepository.findByPumabus(pumabus));
            if(bicipuma != "")
                resultado = intersecta(resultado,localRepository.findByBiciPuma(bicipuma));
            if(metrobus != "")
                resultado = intersecta(resultado,localRepository.findByMetroBus(metrobus));
        return resultado;
        }
    }


    private List<Local> intersecta(List<Local> lista1,List<Local> lista2){
        if(lista1 == null)
            return lista2;
        if(lista2 == null)
            return lista1;
        
        List<Local> resultado = new ArrayList<Local>();
        
        for (Iterator<Local> it = lista1.iterator(); it.hasNext();) {
            Local local = it.next();
            if(lista2.contains(local))
                resultado.add(local);
        }
        return resultado;
    }

    @Override
    public List<Pumabus> findAllPumabus() {
        return pumabusRepository.findAll();
    }

    @Override
    public List<Metrobus> findAllMetrobus() {
        return metrobusRepository.findAll();
    }

    @Override
    public List<Metro> findAllMetro() {
        return metroRepository.findAll();
    }

    @Override
    public List<BiciPuma> findAllBiciPuma() {
        return bicipumaRepository.findAll();
    }

    @Override
    public List<Local> findByNombre(String nombre,Boolean admin) {
        if(admin)
            return localRepository.findByNombreAdmin(nombre);
        return localRepository.findByNombre(nombre);
    }

    @Override
    public List<Local> findByPunto(Double lat, Double lon) {
return localRepository.findByPunto(lat, lon);
        }

    @Override
    public List<Facultad> findAllFacultades() {
return facultadRepository.findAll();
        }

    @Override
    public List<Comentario> findComentarios(Integer id) {
        return comentarioRepository.findByLocalID(id);
    }

    @Override
    public void actualizaCalificacion(Local l) {
        //
        
        for(Comentario coment:l.getComentarios()){
            coment.setLocal(l);
        }
        
        try{
        //l.setCalificacion(localRepository.getPromedio(l.getNombre()).intValue());
            int calif = 0;
            for (Comentario c: l.getComentarios()){
                calif+=c.getCalificacion();
            }
            calif = calif /l.getComentarios().size();
            l.setCalificacion(calif);
        }catch(Exception e){
       /* localRepository.save(l);
                l.setCalificacion(localRepository.getPromedio(l.getNombre()).intValue());
        localRepository.save(l);
*/
        }
        localRepository.save(l);
    }

    @Override
    public List<Categoria> findEspecialidades() {
    return categoriaRepository.findEspecialidades();
        }
    
    @Override
    public void guardaCategoria(Categoria c) {
    //categoriaRepository.save(c);
        }

    @Override
    public Local findById(Integer id) {
        List<Local> result =localRepository.findById(id);
        if (result.size() > 0)return result.get(0);
        return null;    }
    

    public List<Local> findAllAdmin(){
         List<Local> result = localRepository.findByAprobado(Boolean.FALSE);
         result.addAll(localRepository.findByAprobado(Boolean.TRUE));
        return result;
    }

    public List<Local> finNoAprobados(){
        return localRepository.findByAprobado(Boolean.FALSE);
    }
    
    public byte[] findFoto(Integer id){
        return localRepository.findFoto(id);
    }
 
    
       public void guardaComentario(Comentario c){
        comentarioRepository.save(c);
        
    }

    @Override
    public void guardaFacultad(Facultad facultad) {

facultadRepository.save(facultad);
    }

    @Override
    public void creaLocal(Local local) {
for (Categoria c : local.getCategorias())
    c.setLocal(local);
localRepository.save(local);
    }

    @Override
    public ArrayList<Usuario> findUsuarios() {
return (ArrayList<Usuario>) usuarioRepository.findAll();
        }
    
    @Override
    public void guardaUsuario(Usuario usuario) {
        if (usuario.isAdministrador())usuario.setRol("ROLE_ADMIN");
        else usuario.setRol("ROLE_USER");
        usuarioRepository.save(usuario);
    }
    
    
}
