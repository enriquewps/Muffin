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
import mx.unam.pixel.repository.BiciPumaRepository;
import mx.unam.pixel.repository.CategoriaRepository;
import mx.unam.pixel.repository.FacultadRepository;
import mx.unam.pixel.repository.MetroRepository;
import mx.unam.pixel.repository.MetrobusRepository;
import mx.unam.pixel.repository.PumabusRepository;
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
    
    //Hayq eu quitar por que estan en la faculad
    
    @Override
    public void guardaLocal(Local local) {
        
        localRepository.save(local);
       
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
    public List<Local> busquedaAvanzada(Boolean aprobado, String nombre, String categoria,
            Integer rangoInferior, Integer rangoSuperior, Boolean wifi, Boolean estacionamiento,
            Integer comeOLleva, String facultad, String pumabus, String bicipuma, String metro,
            String metrobus, Boolean admin){
        
        List<Local> resultado;

        if(admin){
            if(!aprobado){
                resultado = localRepository.findByAprobado(aprobado);
            }else{
                resultado = localRepository.findAll();
            }
        }else
            resultado = localRepository.findByAprobado(aprobado);
        if(nombre != "")
            resultado = intersecta(resultado,localRepository.findByNombre(nombre));
        if(categoria != "")
            resultado = intersecta(resultado,localRepository.findByCategoria(categoria));
        resultado = intersecta(resultado,localRepository.findByRangoInferior(rangoInferior, rangoSuperior));
        if(wifi)
            resultado = intersecta(resultado,localRepository.findByWifi());
        if(estacionamiento)
            resultado = intersecta(resultado,localRepository.findByEstacionamiento());
        if(comeOLleva == 1 || comeOLleva == 3)
            resultado = intersecta(resultado,localRepository.findByComer());
        if(comeOLleva == 2 || comeOLleva == 3)
            resultado = intersecta(resultado,localRepository.findByLlevar());
        if(facultad != "")
            resultado = intersecta(resultado,localRepository.findByFacultad(facultad));
        if(pumabus != "")
            resultado = intersecta(resultado,localRepository.findByPumabus(pumabus));
        if(bicipuma != "")
            resultado = intersecta(resultado,localRepository.findByBiciPuma(bicipuma));
        if(metro != "")
            resultado = intersecta(resultado,localRepository.findByBiciPuma(metro));
        return resultado;
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
    public List<Local> findByNombre(String nombre) {
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
    public List<Comentario> findComentarios(String local) {
        return localRepository.findComentarios(local);
    }

    @Override
    public void actualizaCalificacion(Local l) {
        //
       // l.setCalificacion(localRepository.getPromedio(l.getNombre()));
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
         result.addAll(localRepository.findByAprobado(Boolean.FALSE));
        return result;
    }

    public List<Local> finNoAprobados(){
        return localRepository.findByAprobado(Boolean.FALSE);
    }
}