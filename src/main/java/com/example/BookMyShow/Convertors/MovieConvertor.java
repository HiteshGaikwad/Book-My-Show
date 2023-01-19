package com.example.BookMyShow.Convertors;

import com.example.BookMyShow.Models.MovieEntity;
import com.example.BookMyShow.RequestDto.MovieRequestDto;

public class MovieConvertor {

    public static MovieEntity convertRequestDtoToMovie(MovieRequestDto movieRequestDto){

        MovieEntity movie= MovieEntity.builder().movieName(movieRequestDto.getMovieName()).duration(movieRequestDto.getDuration())
                .releaseDate(movieRequestDto.getReleaseDate())
                .build();

        return movie;
    }

}
