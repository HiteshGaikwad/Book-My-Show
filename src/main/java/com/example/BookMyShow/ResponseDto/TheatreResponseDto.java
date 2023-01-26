package com.example.BookMyShow.ResponseDto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TheatreResponseDto {

    private int id;

    private String name;

    private String city;

    private String address;

    private int numberOfSeats;
}
