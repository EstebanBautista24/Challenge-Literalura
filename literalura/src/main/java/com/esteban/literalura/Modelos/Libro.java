package com.esteban.literalura.Modelos;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "libro_seq")
    @SequenceGenerator(name = "libro_seq", sequenceName = "libro_sequence", allocationSize = 1)
    private long idLibro;

    @Column(unique = true, nullable = false)
    private String titulo;

    @ManyToOne
    @JoinColumn(referencedColumnName = "idAutor")
    private Autor autor;
    private String idioma;
    private Integer descargas;


    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.getTitulo();
        this.idioma = datosLibro.getIdioma().get(0);
        this.descargas = datosLibro.getDescargas();
        this.autor = new Autor(datosLibro.getAutores().get(0));
    }

    @Override
    public String toString() {
        return " -----------------Libro:-------------------" +
                "\n titulo='" + titulo + '\'' +
                "\n autor=" + autor.getNombre() +
                "\n idioma='" + idioma + '\'' +
                "\n descargas=" + descargas+
                "\n -----------------------------------------";

    }
}
