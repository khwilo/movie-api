package com.khwilo.movieapi.dao;

import com.khwilo.movieapi.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findMovieByTitle(String title);
    Optional<Movie> findByIdAndUserId(Long id, Long userId);
}
