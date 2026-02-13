package com.higuitar.movie_managment.controller;

import com.higuitar.movie_managment.model.dto.MovieRequestDto;
import com.higuitar.movie_managment.model.dto.MovieResponseDto;
import com.higuitar.movie_managment.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("api/movies")
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieResponseDto> createMovie
            (@Valid @RequestBody MovieRequestDto requestDto){
        MovieResponseDto created = movieService.createMovie(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDto> getMovieById(@PathVariable Long id) {

        MovieResponseDto getMovie = movieService.getMovieById(id);
        return ResponseEntity.status(HttpStatus.OK).body(getMovie);
    }

    @GetMapping()
    public ResponseEntity<List<MovieResponseDto>> getAllMovies(
            @RequestParam(required = false) String director,
            @RequestParam(required = false) String genre){

        List<MovieResponseDto> moviesList = movieService.getAllMovies(director, genre);
        return ResponseEntity.status(HttpStatus.OK).body(moviesList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDto> updateMovie(
            @PathVariable Long id,
            @RequestBody MovieRequestDto movieDto){

        MovieResponseDto update = movieService.updateMovie(id, movieDto);
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id){

        movieService.deleteMovie(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
