package com.example.BookMyShow.ResponseDto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntityResponseDto {

    private int id;

    private  String name;

    private String mobile;

    private int age;

    private Date createdOn;
}
