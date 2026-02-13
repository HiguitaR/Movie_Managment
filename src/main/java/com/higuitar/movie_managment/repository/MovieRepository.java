package com.higuitar.movie_managment.repository;

import com.higuitar.movie_managment.model.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    //Busca películas cuyo campo director contenga el texto,
    //Ignora si es en mayúsculas o minúsculas.
    List<MovieEntity> findByDirectorContainingIgnoreCase(String director);
    //Busca películas que coincidan exactamente con el género,
    // pero sin importar las mayúsculas/minúsculas
    List<MovieEntity> findByGenreIgnoreCase(String genre);
    //Busca una película por su título exacto.
    //Devuelve un Optional. Esto es una buena práctica en Java para evitar errores de
    // "NullPointerException". Te obliga a verificar si la película existe (isPresent())
    // antes de usarla.
    Optional<MovieEntity> findByTitle(String title);

}
