package com.example.BookMyShow.Service;


import com.example.BookMyShow.Convertors.ShowConvertor;
import com.example.BookMyShow.Convertors.TheatreConvertor;
import com.example.BookMyShow.Enums.SeatType;
import com.example.BookMyShow.Models.MovieEntity;
import com.example.BookMyShow.Models.ShowEntity;
import com.example.BookMyShow.Models.TheatreEntity;
import com.example.BookMyShow.Models.TheatreSeatEntity;
import com.example.BookMyShow.Repository.MovieRepository;
import com.example.BookMyShow.Repository.ShowRepository;
import com.example.BookMyShow.Repository.TheatreRepository;
import com.example.BookMyShow.Repository.TheatreSeatRepository;
import com.example.BookMyShow.RequestDto.TheatreRequestDto;
import com.example.BookMyShow.ResponseDto.ShowEntityResponseDto;
import com.example.BookMyShow.ResponseDto.TheatreResponseDto;
import com.example.BookMyShow.ResponseDto.UserEntityResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreService {

    @Autowired
    TheatreSeatRepository theatreSeatRepository;

    @Autowired
    TheatreRepository theatreRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    MovieRepository movieRepository;

    public void createTheatre(TheatreRequestDto theatreRequestDto){

        TheatreEntity theatre= TheatreConvertor.convertRequestDtoToTheatre(theatreRequestDto);

        List<TheatreSeatEntity> theatreSeatEntityList= createTheatreSeats();

        theatre.setTheatreSeatEntityList(theatreSeatEntityList);

        for(TheatreSeatEntity theatreSeat: theatreSeatEntityList){
            theatreSeat.setTheatre(theatre);
        }

        theatreRepository.save(theatre);
    }

    public List<TheatreSeatEntity> createTheatreSeats(){

        List<TheatreSeatEntity> seats=new ArrayList<>();

        for(int i=0; i<5; i++){

            char seatNo= (char)(i+'A');

            TheatreSeatEntity theatreSeat=new TheatreSeatEntity("1"+seatNo, SeatType.CLASSIC,100);
            seats.add(theatreSeat);

            TheatreSeatEntity theatreSeat2=new TheatreSeatEntity("2"+seatNo, SeatType.PLATINUM,200);
            seats.add(theatreSeat2);
        }
        theatreSeatRepository.saveAll(seats);

      return seats;
    }


    public List<String> getAllTheatreList(String movieName){

        List<String> theatreList=new ArrayList<>();

        MovieEntity movieEntity= movieRepository.findByMovieName(movieName);

        List<ShowEntity> showEntityList= movieEntity.getListOfShows();

        for(ShowEntity showEntity: showEntityList) {
            TheatreEntity theatre = showEntity.getTheatre();

            theatreList.add(theatre.getName());
        }
        return theatreList;
    }

    public List<TheatreResponseDto> getAllTheatres(){
        List<TheatreEntity> theatreEntityList= theatreRepository.findAll();

        List<TheatreResponseDto> theatreResponseDtoList= new ArrayList<>();

        for(TheatreEntity theatre: theatreEntityList){

            TheatreResponseDto theatreResponseDto= TheatreConvertor.convertTheatreToResponseDto(theatre);

            theatreResponseDtoList.add(theatreResponseDto);
        }

        return theatreResponseDtoList;
    }

    public TheatreResponseDto getTheatreById(int id){

        TheatreEntity theatreEntity= theatreRepository.findById(id).get();

        TheatreResponseDto theatreResponseDto= TheatreConvertor.convertTheatreToResponseDto(theatreEntity);

        return theatreResponseDto;
    }

    public List<ShowEntityResponseDto> getShowListInTheatreByTheatreId(int id){
        TheatreEntity theatre= theatreRepository.findById(id).get();

        List<ShowEntity> showEntityList= theatre.getListOfShows();

        List<ShowEntityResponseDto> showEntityResponseDtoList= new ArrayList<>();
        for(ShowEntity shows: showEntityList){

            ShowEntityResponseDto showEntityResponseDto= ShowConvertor.convertShowEntityToResponseDto(shows);
            showEntityResponseDtoList.add(showEntityResponseDto);
        }
        return showEntityResponseDtoList;
    }

    public List<ShowEntityResponseDto> getShowListInTheatreByTheatreName(String name){
        TheatreEntity theatre= theatreRepository.findByName(name);


        List<ShowEntity> showEntityList= theatre.getListOfShows();

        if(showEntityList==null){
            showEntityList= new ArrayList<>();
        }

        List<ShowEntityResponseDto> showEntityResponseDtoList= new ArrayList<>();
        for(ShowEntity shows: showEntityList){

            ShowEntityResponseDto showEntityResponseDto= ShowConvertor.convertShowEntityToResponseDto(shows);
            showEntityResponseDtoList.add(showEntityResponseDto);
        }
        return showEntityResponseDtoList;
    }
}
