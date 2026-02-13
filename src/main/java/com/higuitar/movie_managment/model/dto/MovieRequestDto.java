package com.higuitar.movie_managment.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

@JsonInclude(JsonInclude.Include.NON_NULL) //Los campos nulos no se incluyen el el json
public record MovieRequestDto (

    @NotBlank(message = "El title es obligatorio")
    @Size(min = 2, max = 100, message = "El title debe tener entre 2 y 100 caracteres")
    String title,

    @NotBlank(message = "El director es obligatorio")
    @Size(min = 2, max = 100, message = "El director debe tener entre 2 y 100 caracteres")
    String director,

    @NotBlank(message = "EL genero es obligatorio")
    @Pattern(regexp = "^(Action|Comedy|Drama|Horror|Sci-Fi|Thriller|Animation|Documentary)$",
        message = "Genero invalido, debe ser uno de los predefinidos")
    String genre,

    @NotNull(message = "El año de lanzamiento es obligatorio")
    @Min(value = 1888, message = "El año de lanzamiento no puede ser anterior a 1888 (inicio del cine).")
    @Max(value = 2026, message = "El año de lanzamiento no puede ser posterior al año actual (2025).")
    @JsonProperty("year_released")
    Integer releaseYear,

    @Min(value = 1, message = "La calificación IMDb debe ser al menos 1")
    @Max(value = 10, message = "La calificación IMDb no puede exceder 10")
    @JsonProperty("imdb_score")
    Double imdbRating){}

