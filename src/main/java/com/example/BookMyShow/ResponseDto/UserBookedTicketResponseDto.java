package com.example.BookMyShow.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserBookedTicketResponseDto {

    private String movieName;

    private String theatreName;

    private Date bookedDate;

    private LocalTime bookedTime;

    private int amount;
}
