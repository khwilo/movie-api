package com.khwilo.movieapi.controller;

import com.khwilo.movieapi.dao.MovieRepository;
import com.khwilo.movieapi.dao.UserRepository;
import com.khwilo.movieapi.exception.ResourceNotFoundException;
import com.khwilo.movieapi.model.Movie;
import com.khwilo.movieapi.model.User;
import com.khwilo.movieapi.payload.MovieRequest;
import com.khwilo.movieapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping()
    public ResponseEntity<Object> getAllMovies() {
        if (movieService.getAllMovies() == null) {
            return new ResponseEntity<>("There are no currently added movies", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<Object> createMovie(@Valid @RequestBody MovieRequest movieRequest) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        User currentUser = userRepository.findUserByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException(username, "username", "String"));
        Long userId = currentUser.getId();

        Movie movie = new Movie(
                movieRequest.getTitle(), movieRequest.getDescription()
        );
        userRepository.findById(userId).map(user -> {
            movie.setUser(user);
            return movieRepository.save(movie);
        });

//        movieService.save(movie);
        return new ResponseEntity<>("Movie created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMovie(@PathVariable("id") String id, @RequestBody Movie movie) {
        Long movieId = Long.parseLong(id);
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if (!movieOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        movieService.updateMovie(movieId, movie);
        return new ResponseEntity<>("Movie updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMovie(@PathVariable("id") String id) {
        Long movieId = Long.parseLong(id);
        movieService.deleteMovie(movieId);
        return new ResponseEntity<>("Movie deleted successfully", HttpStatus.OK);
    }
}
