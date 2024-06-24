package com.esteban.literalura.Servicios;

public interface IConvertible {
    public <T> T convertirDatos(String json,Class<T> clase);
}
