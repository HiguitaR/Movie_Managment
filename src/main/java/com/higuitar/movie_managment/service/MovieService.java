package com.higuitar.movie_managment.service;

import com.higuitar.movie_managment.model.dto.MovieRequestDto;
import com.higuitar.movie_managment.model.dto.MovieResponseDto;

import java.util.List;

public interface MovieService {

    MovieResponseDto createMovie(MovieRequestDto movieRequestDto);

    MovieResponseDto getMovieById(Long id);

    List<MovieResponseDto> getAllMovies(String director, String genre);

    MovieResponseDto updateMovie(Long id, MovieRequestDto movieRequestDto);
}
