package com.example.BookMyShow.Convertors;

import com.example.BookMyShow.Models.ShowEntity;
import com.example.BookMyShow.RequestDto.ShowRequestDto;
import com.example.BookMyShow.ResponseDto.ShowEntityResponseDto;

public class ShowConvertor {

    public static ShowEntity convertRequestDtoToShowEntity(ShowRequestDto showRequestDto){

        ShowEntity showEntity=ShowEntity.builder().showTime(showRequestDto.getShowTime())
                .showDate(showRequestDto.getShowDate()).multiplier(showRequestDto.getMultiplier())
        .build();

        return showEntity;
    }

    public static ShowEntityResponseDto convertShowEntityToResponseDto(ShowEntity showEntity){

        ShowEntityResponseDto showEntityResponseDto= ShowEntityResponseDto.builder().id(showEntity.getId()).
                showDate(showEntity.getShowDate()).showTime(showEntity.getShowTime()).createdOn(showEntity.getCreatedOn())
                .movieName(showEntity.getMovie().getMovieName()).theatreName(showEntity.getTheatre().getName())
                .multiplier(showEntity.getMultiplier()).updatedOn(showEntity.getUpdatedOn())
                .build();

        return showEntityResponseDto;

    }
}
