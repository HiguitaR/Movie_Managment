package com.higuitar.movie_managment.mapper;

import com.higuitar.movie_managment.model.dto.MovieRequestDto;
import com.higuitar.movie_managment.model.dto.MovieResponseDto;
import com.higuitar.movie_managment.model.entity.MovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(target = "id", ignore = true)
    MovieEntity toEntity(MovieRequestDto requestDto);

    MovieResponseDto toResponse(MovieEntity entity);
}
