package com.example.BookMyShow.Service;


import com.example.BookMyShow.Convertors.MovieConvertor;
import com.example.BookMyShow.Models.MovieEntity;
import com.example.BookMyShow.Repository.MovieRepository;
import com.example.BookMyShow.RequestDto.MovieRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public void addMovie(MovieRequestDto movieRequestDto){

        MovieEntity movie= MovieConvertor.convertRequestDtoToMovie(movieRequestDto);

        movieRepository.save(movie);
    }

    public MovieEntity getMovieByName(String movieName){
        return movieRepository.findByMovieName(movieName);
    }
}
