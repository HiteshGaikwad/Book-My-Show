package com.example.BookMyShow.Controller;


import com.example.BookMyShow.RequestDto.TheatreRequestDto;
import com.example.BookMyShow.Service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theatre")
public class TheatreController {

    @Autowired
    TheatreService theatreService;

    @PostMapping("/add")
    public ResponseEntity<String> addTheatre(@RequestBody() TheatreRequestDto theatreRequestDto){

        theatreService.createTheatre(theatreRequestDto);

        return new ResponseEntity<>("Theatre created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/get-all-theatreList")
    public ResponseEntity<List<String>> getAllTheatreList(@RequestParam("movieName") String movieName){
        List<String> theatreList= theatreService.getAllTheatreList(movieName);
        return new ResponseEntity<>(theatreList,HttpStatus.FOUND);
    }
}
