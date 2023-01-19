package com.example.BookMyShow.Convertors;

import com.example.BookMyShow.Models.TheatreEntity;
import com.example.BookMyShow.RequestDto.TheatreRequestDto;

public class TheatreConvertor {

    public static TheatreEntity convertRequestDtoToTheatre(TheatreRequestDto theatreRequestDto){

        TheatreEntity theatre=TheatreEntity.builder().name(theatreRequestDto.getName()).city(theatreRequestDto.getCity()).
                address(theatreRequestDto.getAddress())
                .build();

        return theatre;
    }
}
