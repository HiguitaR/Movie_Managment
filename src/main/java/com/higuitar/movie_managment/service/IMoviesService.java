package com.higuitar.movie_managment.service;

import com.higuitar.movie_managment.exception.MovieAlreadyExistException;
import com.higuitar.movie_managment.mapper.MovieMapper;
import com.higuitar.movie_managment.model.dto.MovieRequestDto;
import com.higuitar.movie_managment.model.dto.MovieResponseDto;
import com.higuitar.movie_managment.model.entity.MovieEntity;
import com.higuitar.movie_managment.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class IMoviesService implements MovieService{

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Override
    public MovieResponseDto createMovie(MovieRequestDto movieRequestDto) {

        if(movieRepository.findByTitle(movieRequestDto.title()).isPresent()){
            throw new MovieAlreadyExistException();
        }
        MovieEntity movie = movieMapper.toEntity(movieRequestDto);
        movieRepository.save(movie);
        return movieMapper.toResponse(movie);
    }

    @Override
    public MovieResponseDto getMovieById(Long id) {
        return null;
    }

    @Override
    public List<MovieResponseDto> getAllMovies(String director, String genre) {
        return List.of();
    }

    @Override
    public MovieResponseDto updateMovie(Long id, MovieRequestDto movieRequestDto) {
        return null;
    }
}
