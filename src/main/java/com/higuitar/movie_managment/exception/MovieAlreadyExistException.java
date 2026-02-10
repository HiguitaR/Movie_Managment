package com.higuitar.movie_managment.exception;

public class MovieAlreadyExistException extends RuntimeException{
    public MovieAlreadyExistException() {
        this("La pelicula ya existe en el sistema!");
    }

    public MovieAlreadyExistException(String message) {
        super(message);
    }
}
