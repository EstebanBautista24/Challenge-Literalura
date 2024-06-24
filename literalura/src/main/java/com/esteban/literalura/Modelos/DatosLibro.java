package com.esteban.literalura.Modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatosLibro {
    @JsonAlias("title")
    private String titulo;
    @JsonAlias("authors")
    private List<DatosAutor> autores;
    @JsonAlias("languages")
    private List<String> idioma;
    @JsonAlias("download_count")
    private Integer descargas;
}
