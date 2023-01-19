package com.example.BookMyShow.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowRequestDto {

    private LocalDate showDate;

    private LocalTime showTime;

    private  String movieName;

    private int theatreId;

    private double multiplier;

}
