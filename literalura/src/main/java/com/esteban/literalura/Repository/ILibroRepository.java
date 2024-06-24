package com.esteban.literalura.Repository;

import com.esteban.literalura.Modelos.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ILibroRepository extends JpaRepository<Libro, Integer> {

    @Query("SELECT L FROM Libro L WHERE L.idioma = :idioma")
    List<Libro> listaLibrosIdioma(String idioma);

    @Query("SELECT L FROM Libro L ORDER BY L.descargas DESC LIMIT 10")
    List<Libro> top10Libros();

    @Query("SELECT L FROM Libro L WHERE L.titulo ILIKE  %:nombre%")
    Optional<Libro> libroPorNombre(String nombre);
}
