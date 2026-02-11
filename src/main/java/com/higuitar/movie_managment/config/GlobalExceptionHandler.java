package com.higuitar.movie_managment.config;

import com.higuitar.movie_managment.exception.MovieAlreadyExistException;
import com.higuitar.movie_managment.exception.MovieNotFoundException;
import com.higuitar.movie_managment.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MovieAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiErrorResponse handlerMovieAlreadyExist(MovieAlreadyExistException ex,
                                                     HttpServletRequest request){
        return new ApiErrorResponse(HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                request.getRequestURI());
    }

    @ExceptionHandler(MovieNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handlerMovieNotFound(MovieNotFoundException ex,
                                                 HttpServletRequest request){
        return new ApiErrorResponse(HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getRequestURI());
    }

    @ExceptionHandler(MovieNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handlerResourceNotFound(ResourceNotFoundException ex,
                                                    HttpServletRequest request){
        return new ApiErrorResponse(HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return new ApiErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed",
                request.getRequestURI(),
                errors
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse handleGeneral(Exception ex, HttpServletRequest request) {
        return new ApiErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred",
                request.getRequestURI()
        );
    }

}
