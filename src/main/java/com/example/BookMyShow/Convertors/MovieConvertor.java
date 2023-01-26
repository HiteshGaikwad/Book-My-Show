package com.example.BookMyShow.Convertors;

import com.example.BookMyShow.Models.MovieEntity;
import com.example.BookMyShow.RequestDto.MovieRequestDto;
import com.example.BookMyShow.ResponseDto.MovieResponseDto;

public class MovieConvertor {

    public static MovieEntity convertRequestDtoToMovie(MovieRequestDto movieRequestDto){

        MovieEntity movie= MovieEntity.builder().movieName(movieRequestDto.getMovieName()).duration(movieRequestDto.getDuration())
                .releaseDate(movieRequestDto.getReleaseDate())
                .build();

        return movie;
    }

    public static MovieResponseDto convertMovieEntityToResponseDto(MovieEntity movieEntity){

        MovieResponseDto movieResponseDto= MovieResponseDto.builder().id(movieEntity.getId()).movieName(movieEntity.getMovieName())
                .duration(movieEntity.getDuration()).releaseDate(movieEntity.getReleaseDate())
                .build();

        return movieResponseDto;
    }
}
