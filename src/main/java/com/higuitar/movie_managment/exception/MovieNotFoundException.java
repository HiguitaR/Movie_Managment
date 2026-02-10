package com.higuitar.movie_managment.exception;

public class MovieNotFoundException extends RuntimeException{

    public MovieNotFoundException() {
        this("La movie no existe en el sistema!");
    }

    public MovieNotFoundException(String message) {
        super(message);
    }
}
