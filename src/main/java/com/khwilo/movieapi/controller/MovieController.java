package com.khwilo.movieapi.controller;

import com.khwilo.movieapi.model.Movie;
import com.khwilo.movieapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.MarshalledObject;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/api/movies")
    public ResponseEntity<Object> getAllMovies() {
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }

    @PostMapping("/api/movies")
    public ResponseEntity<Object> createMovie(@RequestBody Movie movie) {
        movieService.save(movie);
        return new ResponseEntity<>("Movie created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/api/movies/{id}")
    public ResponseEntity<Object> updateMovie(@PathVariable("id") String id, @RequestBody Movie movie) {
        int movieId = Integer.parseInt(id);
        movieService.updateMovie(movieId, movie);
        return new ResponseEntity<>("Movie updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/api/movies/{id}")
    public ResponseEntity<Object> deleteMovie(@PathVariable("id") String id) {
        int movieId = Integer.parseInt(id);
        movieService.deleteMovie(movieId);
        return new ResponseEntity<>("Movie deleted successfully", HttpStatus.OK);
    }
}
