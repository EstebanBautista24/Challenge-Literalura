package com.esteban.literalura.Servicios;

import com.esteban.literalura.Modelos.Autor;
import com.esteban.literalura.Repository.IAutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {
    private IAutorRepository autorRepository;
    public AutorService(@Autowired IAutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }
    public void GuardarAutor(Autor autor) {
        autorRepository.save(autor);
    }
    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }
    public List<Autor> listarAutorVivo(Integer fecha){
        return autorRepository.listarAutorFecha(fecha);
    }
    public Autor buscarAutorPorNombre(String nombre) {
        if(autorRepository.autorPorNombre(nombre).isPresent()){
            return autorRepository.autorPorNombre(nombre).get();
        }else{
                return null;
    }
    }

}
