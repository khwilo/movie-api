package com.khwilo.movieapi.service;

import com.khwilo.movieapi.dao.MovieRepository;
import com.khwilo.movieapi.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public Movie getMovieByTitle(String title) {
        return movieRepository.findMovieByTitle(title);
    }

    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        movieRepository.findAll().forEach(
                movie -> movies.add(movie)
        );
        return movies;
    }

    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    public void updateMovie(int id, Movie movie) {
        movie.setId(id);
        movieRepository.save(movie);
    }

    public void deleteMovie(int id) {
        movieRepository.deleteById(id);
    }


}
