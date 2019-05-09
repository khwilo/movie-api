package com.khwilo.movieapi.dao;

import com.khwilo.movieapi.model.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
    Movie findMovieByTitle(String title);
}
