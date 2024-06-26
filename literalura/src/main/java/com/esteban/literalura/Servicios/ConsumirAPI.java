package com.esteban.literalura.Servicios;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumirAPI {
    public static String consumoApi(String url){
        HttpClient cliente= HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> respuesta;
        try {
            respuesta =cliente.send(solicitud,HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return  respuesta.body();
    }
}
