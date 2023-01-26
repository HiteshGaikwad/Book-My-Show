package com.example.BookMyShow.ResponseDto;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieResponseDto {

    private int id;

    private String movieName;

    private int duration;

    private Date releaseDate;
}
