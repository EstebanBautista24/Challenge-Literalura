package com.esteban.literalura.Principal;

import com.esteban.literalura.Modelos.Autor;
import com.esteban.literalura.Modelos.DatosLibro;
import com.esteban.literalura.Modelos.Libro;
import com.esteban.literalura.Modelos.Respuesta;
import com.esteban.literalura.Servicios.AutorService;
import com.esteban.literalura.Servicios.ConsumirAPI;
import com.esteban.literalura.Servicios.ConvertirDatos;
import com.esteban.literalura.Servicios.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

public class Principal {

    private static LibroService libroService;

    private static AutorService autorService;

    public Principal(LibroService libroService, AutorService autorService){
        this.libroService = libroService;
        this.autorService = autorService;
    }

    private static ConvertirDatos convertirDatos;
    private static final String URL_BASE = "https://gutendex.com/books/";
    private static Scanner sc = new Scanner(System.in);
    public static void principal(){
    menu();

    }
    private static void menu(){
        int op=-1;
        while (op!=0){
        System.out.println("Elija el numero de opcion: ");
        System.out.println("1- Buscar libro por titulo ");
        System.out.println("2- Listar libros registrados");
        System.out.println("3- Listar autores registrados");
        System.out.println("4- Listar autores vivos en un determinado año");
        System.out.println("5- Listar libros por idioma");
        System.out.println("6- Listar top 10 libros");
        System.out.println("7- Buscar libro por titulo");
        System.out.println("8- Buscar autor por nombre");
        System.out.println("0- cerrar programa");
         op= sc.nextInt();
         List<Autor> autores;
         List<Libro> libros;

            sc.nextLine();
        switch(op){
            case 1:
                System.out.println("Escribe titulo del libro: ");
                String titulo = sc.nextLine();
                respuestaAPI(titulo);
                break;
            case 2:
                libros= libroService.librosRegistrados();
                libros.forEach(System.out::println);
                break;
            case 3:
                autores = autorService.listarAutores();
                autores.forEach(System.out::println);
                break;
            case 4:
                System.out.println("Escriba año : ");
                Integer fecha = sc.nextInt();
                sc.nextLine();
                autores = autorService.listarAutorVivo(fecha);
                autores.forEach(System.out::println);
                break;
            case 5:
                menuIdioma();
                break;
            case 6:
                libros = libroService.top10Libros();
                libros.forEach(System.out::println);
            case 7:
                System.out.println("Escriba el nombre del libro: ");
                String nombre = sc.nextLine();
                System.out.println(libroService.buscarLibroPorNombre(nombre));
                break;
            case 8:
                System.out.println("Escriba el nombre del autor: ");
                String nombreAutor = sc.nextLine();
                System.out.println(autorService.buscarAutorPorNombre(nombreAutor));
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
        }

    }
private static void respuestaAPI(String libro){
    convertirDatos = new ConvertirDatos();
    String json = ConsumirAPI.consumoApi(URL_BASE+"?search="+libro.replace(" ","+"));
    Respuesta datos = convertirDatos.convertirDatos(json,Respuesta.class);
    List<Libro> libros = datos.getLibros().stream().map(l->new Libro(l)).toList();


    libroService.guardarLibro(libros.get(0));



}
public static void menuIdioma(){
    System.out.println("Seleccione idioma:");
    System.out.println("1- Español ");
    System.out.println("2- Ingles");
    System.out.println("3- Frances");
    System.out.println("4- Portuges");
    int op2;
    List<Libro> libros;
    op2 = sc.nextInt();
    switch (op2){
        case 1:
            libros = libroService.libroPorIdioma("es");
            libros.forEach(System.out::println);
            break;
        case 2:
            libros = libroService.libroPorIdioma("en");
            libros.forEach(System.out::println);
            break;
        case 3:
            libros = libroService.libroPorIdioma("fr");
            libros.forEach(System.out::println);
            break;
        case 4:
            libros = libroService.libroPorIdioma("pt");
            libros.forEach(System.out::println);
            break;
    }
}
}
