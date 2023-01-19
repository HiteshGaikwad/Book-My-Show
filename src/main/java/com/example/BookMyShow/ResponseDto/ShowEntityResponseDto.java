package com.example.BookMyShow.ResponseDto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowEntityResponseDto {

    private int id;

    private LocalDate showDate;

    private LocalTime showTime;

    private double multiplier;

    private Date createdOn;

    private Date updatedOn;

    private String theatreName;

    private String movieName;

}
