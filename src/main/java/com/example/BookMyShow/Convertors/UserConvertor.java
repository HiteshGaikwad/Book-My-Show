package com.example.BookMyShow.Convertors;

import com.example.BookMyShow.Models.TicketEntity;
import com.example.BookMyShow.Models.UserEntity;
import com.example.BookMyShow.RequestDto.UserRequestDto;
import com.example.BookMyShow.ResponseDto.UserBookedTicketResponseDto;

public class UserConvertor {

    public static UserEntity convertRequestDtoToUser(UserRequestDto userRequestDto){

        UserEntity user= UserEntity.builder().name(userRequestDto.getName()).age(userRequestDto.getAge())
                .mobile(userRequestDto.getMobile())
                .build();

        return user;
    }

    public static UserBookedTicketResponseDto convertToUserBookedTicket(TicketEntity ticketEntity){

        UserBookedTicketResponseDto userBookedTicketResponseDto= UserBookedTicketResponseDto.builder()
                .amount(ticketEntity.getAmount()).bookedDate(ticketEntity.getBookedDate()).movieName(ticketEntity.getShows().getMovie().getMovieName())
                .theatreName(ticketEntity.getShows().getTheatre().getName()).bookedTime(ticketEntity.getShows().getShowTime())
                .build();

        return userBookedTicketResponseDto;
    }
}
