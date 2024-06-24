package com.esteban.literalura.Servicios;

import com.esteban.literalura.Modelos.Autor;

import com.esteban.literalura.Modelos.Libro;
import com.esteban.literalura.Repository.IAutorRepository;
import com.esteban.literalura.Repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {
    private ILibroRepository libroRepository;
    private IAutorRepository autorRepository;
    public LibroService(@Autowired ILibroRepository libroRepository, @Autowired IAutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }
    public void guardarLibro(Libro libro) {
        Autor autor = autorRepository.findByNombre(libro.getAutor().getNombre());
        if (autor == null) {
            autorRepository.save(libro.getAutor());
        }
        else{
            libro.setAutor(autor);
        }
        libroRepository.save(libro);
    }
    public List<Libro> librosRegistrados(){
        return libroRepository.findAll();
    }
    public List<Libro> libroPorIdioma(String idioma){
        return libroRepository.listaLibrosIdioma(idioma);
    }
    public List<Libro> top10Libros(){
        return libroRepository.top10Libros();
    }
    public Libro buscarLibroPorNombre(String nombre){
        if (libroRepository.libroPorNombre(nombre).isPresent()){
            return libroRepository.libroPorNombre(nombre).get();
        }
        else {
            return null;
        }
    }

}
