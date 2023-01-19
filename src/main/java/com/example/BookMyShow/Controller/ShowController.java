package com.example.BookMyShow.Controller;


import com.example.BookMyShow.Models.ShowEntity;
import com.example.BookMyShow.RequestDto.GivenShowRequestDto;
import com.example.BookMyShow.RequestDto.ShowRequestDto;
import com.example.BookMyShow.ResponseDto.ShowEntityResponseDto;
import com.example.BookMyShow.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/add-show")
    public ResponseEntity<String> addShow(@RequestBody()ShowRequestDto showRequestDto){
        showService.addShow(showRequestDto);
        return new ResponseEntity<>("Show added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/get-all-shows-in-given-date-time")
    public ResponseEntity<List<ShowEntityResponseDto>> gelAllShowsInGivenDateTime(@RequestBody() GivenShowRequestDto givenShowRequestDto){

        List<ShowEntityResponseDto> showList= showService.gelAllShowsInGivenDateTime(givenShowRequestDto);
        return new ResponseEntity<>(showList, HttpStatus.FOUND);
    }
}
