package com.higuitar.movie_managment.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        this("No se encontro la pelicula por el nombre dado!");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
