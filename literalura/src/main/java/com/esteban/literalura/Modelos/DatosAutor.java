package com.esteban.literalura.Modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatosAutor {
    @JsonAlias("name")
    private String nombre;
    @JsonAlias("birth_year")
    private Integer anioNacimiento;
    @JsonAlias("death_year")
    private Integer anioFallecimiento;
}
