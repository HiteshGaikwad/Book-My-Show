package com.example.BookMyShow.Controller;


import com.example.BookMyShow.Models.MovieEntity;
import com.example.BookMyShow.RequestDto.MovieRequestDto;
import com.example.BookMyShow.ResponseDto.MovieResponseDto;
import com.example.BookMyShow.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody() MovieRequestDto movieRequestDto){
        movieService.addMovie(movieRequestDto);
        return new ResponseEntity<>("Movie added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/get-movie-by-name/{movieName}")
    public ResponseEntity<MovieResponseDto> getMovieByName(@PathVariable("movieName") String movieName){
        MovieResponseDto movie= movieService.getMovieByName(movieName);
        return new ResponseEntity<>(movie, HttpStatus.FOUND);
    }

    @GetMapping("/get-movie-by-id/{id}")
    public ResponseEntity<MovieResponseDto> getMovieById(@PathVariable("id") int id){
        MovieResponseDto movie= movieService.getMovieById(id);
        return new ResponseEntity<>(movie, HttpStatus.FOUND);
    }
}
