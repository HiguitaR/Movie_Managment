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


}
