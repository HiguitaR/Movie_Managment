package com.higuitar.movie_managment.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record MovieResponseDto(

        Long id,

        String title,

        String director,
        
        String genre,
        
        @JsonProperty("year_released")
        Integer releaseYear,
        
        @JsonProperty("imdb_score")
        Double imdbRating
) {
}
