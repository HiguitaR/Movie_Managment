package com.higuitar.movie_managment.service;

import com.higuitar.movie_managment.exception.MovieAlreadyExistException;
import com.higuitar.movie_managment.exception.MovieNotFoundException;
import com.higuitar.movie_managment.exception.ResourceNotFoundException;
import com.higuitar.movie_managment.mapper.MovieMapper;
import com.higuitar.movie_managment.model.dto.MovieRequestDto;
import com.higuitar.movie_managment.model.dto.MovieResponseDto;
import com.higuitar.movie_managment.model.entity.MovieEntity;
import com.higuitar.movie_managment.repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class IMoviesService implements MovieService{

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Override
    public MovieResponseDto createMovie(MovieRequestDto movieRequestDto) {

        if(movieRepository.findByTitle(
                movieRequestDto.title()).isPresent()){
            throw new MovieAlreadyExistException();
        }
        MovieEntity movie = movieMapper.toEntity(movieRequestDto);
        movieRepository.save(movie);
        return movieMapper.toResponse(movie);
    }

    @Override
    public MovieResponseDto getMovieById(Long id) {

        return movieRepository.findById(id)
                .map(movieMapper::toResponse)
                .orElseThrow(MovieNotFoundException::new);
    }

    @Override
    public List<MovieResponseDto> getAllMovies(String director, String genre) {

        List<MovieEntity> movies;
        boolean hasDirector = director != null && !director.isBlank();
        boolean hasGenre = genre != null && !genre.isBlank();

        if(hasDirector){
            movies = movieRepository.findByDirectorContainingIgnoreCase(director);

        }else if (hasGenre) {
            movies = movieRepository.findByGenreIgnoreCase(genre);
        }else{
            movies = movieRepository.findAll();
        }

        return Optional.ofNullable(movies)
                .filter(m -> !m.isEmpty())
                .orElseThrow(ResourceNotFoundException::new)
                .stream()
                .map(movieMapper::toResponse)
                .toList();
    }

    @Override
    public MovieResponseDto updateMovie(Long id, MovieRequestDto movieRequestDto) {

        var movie = movieRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        movieMapper.UpdateMovieFromDto(movieRequestDto, movie);//Aqui se hace la magia para actualizar la movie

        return movieMapper.toResponse(movieRepository.save(movie));
    }

    @Override
    public void deleteMovie(Long id) {

        MovieEntity movie = movieRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        movieRepository.delete(movie);
    }
}
