package com.example.BookMyShow.Service;


import com.example.BookMyShow.Convertors.MovieConvertor;
import com.example.BookMyShow.Models.MovieEntity;
import com.example.BookMyShow.Repository.MovieRepository;
import com.example.BookMyShow.RequestDto.MovieRequestDto;
import com.example.BookMyShow.ResponseDto.MovieResponseDto;
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

    public MovieResponseDto getMovieByName(String movieName){
        MovieEntity movie= movieRepository.findByMovieName(movieName);

        MovieResponseDto movieResponseDto=  MovieConvertor.convertMovieEntityToResponseDto(movie);

        return movieResponseDto;
    }

    public MovieResponseDto getMovieById(int id){
        MovieEntity movie= movieRepository.findById(id).get();

        MovieResponseDto movieResponseDto= MovieConvertor.convertMovieEntityToResponseDto(movie);

        return movieResponseDto;
    }
}
