package com.higuitar.movie_managment.config;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class ApiErrorResponse {

    private final Integer code;
    private final String message;
    private final String path;
    private final LocalDateTime timestamp = LocalDateTime.now();
    private Map<String, String> errors;

    //Constructors
    public ApiErrorResponse(Integer code, String message, String path) {
        this.code = code;
        this.message = message;
        this.path = path;
    }

    public ApiErrorResponse(Integer code, String message, String path, Map<String, String> errors) {
        this(code, message, path);
        this.errors = errors;
    }

}
