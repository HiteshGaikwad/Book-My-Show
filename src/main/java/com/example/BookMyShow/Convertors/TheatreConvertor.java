package com.example.BookMyShow.Convertors;

import com.example.BookMyShow.Models.TheatreEntity;
import com.example.BookMyShow.RequestDto.TheatreRequestDto;
import com.example.BookMyShow.ResponseDto.TheatreResponseDto;

public class TheatreConvertor {

    public static TheatreEntity convertRequestDtoToTheatre(TheatreRequestDto theatreRequestDto){

        TheatreEntity theatre=TheatreEntity.builder().name(theatreRequestDto.getName()).city(theatreRequestDto.getCity()).
                address(theatreRequestDto.getAddress())
                .build();

        return theatre;
    }

    public static TheatreResponseDto convertTheatreToResponseDto(TheatreEntity theatreEntity){

        TheatreResponseDto theatreResponseDto= TheatreResponseDto.builder().id(theatreEntity.getId()).name(theatreEntity.getName())
                .city(theatreEntity.getCity()).address(theatreEntity.getAddress())
                .build();

        int numberOfSeats=theatreEntity.getTheatreSeatEntityList().size();

        theatreResponseDto.setNumberOfSeats(numberOfSeats);

        return theatreResponseDto;
    }
}
