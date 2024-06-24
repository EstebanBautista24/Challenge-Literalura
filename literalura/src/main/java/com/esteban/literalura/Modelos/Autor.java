package com.esteban.literalura.Modelos;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@NoArgsConstructor
@Data
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "autor_seq")
    @SequenceGenerator(name = "autor_seq", sequenceName = "autor_sequence", allocationSize = 1)
    private long idAutor;
    private String nombre;
    private Integer anioNacimiento;
    private Integer anioFallecimiento;

    @OneToMany(mappedBy = "autor",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor(DatosAutor datosAutor){
        this.nombre = datosAutor.getNombre();
        if(datosAutor.getAnioNacimiento() != null){
            this.anioNacimiento = datosAutor.getAnioNacimiento();
        }
        if(datosAutor.getAnioFallecimiento()!=null){
            this.anioFallecimiento = datosAutor.getAnioFallecimiento();
        }

    }

    @Override
    public String toString() {
        return " -----------------Autor:-------------------\n" +
                " nombre=" + nombre +
                "\n añoNacimiento=" + anioNacimiento +
                "\n añoFallecimiento='" + anioFallecimiento+
                "\n libros=" + libros.stream().map(Libro::getTitulo)
                .collect(Collectors.joining(", "))+
                "\n -----------------------------------------";

    }
}
