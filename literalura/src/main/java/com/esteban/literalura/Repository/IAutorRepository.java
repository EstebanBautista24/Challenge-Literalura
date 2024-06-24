package com.esteban.literalura.Repository;

import com.esteban.literalura.Modelos.Autor;
import com.esteban.literalura.Modelos.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface IAutorRepository extends JpaRepository<Autor, Integer> {

    @Query("SELECT A FROM Autor  A WHERE A.nombre LIKE :nombre")
    Autor findByNombre(String nombre);

    @Query("SELECT A FROM Autor A WHERE A.anioFallecimiento>:fecha")
    List<Autor>listarAutorFecha(Integer fecha);

    @Query("SELECT A FROM Autor A WHERE A.nombre ILIKE  %:nombre%")
    Optional<Autor> autorPorNombre(String nombre);
}
