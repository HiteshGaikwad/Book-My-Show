package com.example.BookMyShow.Service;


import com.example.BookMyShow.Convertors.ShowConvertor;
import com.example.BookMyShow.Models.*;
import com.example.BookMyShow.Repository.MovieRepository;
import com.example.BookMyShow.Repository.ShowSeatsRepository;
import com.example.BookMyShow.Repository.TheatreRepository;
import com.example.BookMyShow.RequestDto.GivenShowRequestDto;
import com.example.BookMyShow.RequestDto.ShowRequestDto;
import com.example.BookMyShow.ResponseDto.ShowEntityResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

@Service
public class ShowService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheatreRepository theatreRepository;

    @Autowired
    ShowSeatsRepository showSeatsRepository;

    @Autowired
    TheatreService theatreService;

//    @Autowired
//    ShowRepository showRepository;

    public void addShow(ShowRequestDto showRequestDto){


        ShowEntity show= ShowConvertor.convertRequestDtoToShowEntity(showRequestDto);


        //add movie to the showEntity by movieName

        MovieEntity movie= movieRepository.findByMovieName(showRequestDto.getMovieName());

        //add theatre to the showEntity by theatreId

        TheatreEntity theatre= theatreRepository.findById(showRequestDto.getTheatreId()).get();

        //set movie and theatre to the showEntity

        show.setMovie(movie);
        show.setTheatre(theatre);

        movie.getListOfShows().add(show);
        theatre.getListOfShows().add(show);


        //add showSeatsEntity ti the show
        List<ShowSeatEntity> seatEntityList= createShowSeats(theatre.getTheatreSeatEntityList());

        show.setListOfSeats(seatEntityList);

        //for each showSeat we need to mark that to which is belongs (foreign key will be filled)
        for(ShowSeatEntity showSeat: seatEntityList){
            showSeat.setShows(show);

        }

        movieRepository.save(movie);
        theatreRepository.save(theatre);

    }

    public List<ShowSeatEntity> createShowSeats(List<TheatreSeatEntity> theatreSeatEntityList){

        List<ShowSeatEntity> seats=new ArrayList<>();

        for(TheatreSeatEntity theatreSeat: theatreSeatEntityList){

            ShowSeatEntity showSeatEntity=ShowSeatEntity.builder().seatNo(theatreSeat.getSeatNo()).seatType(theatreSeat.getSeatType())
                    .build();

            seats.add(showSeatEntity);
        }

        showSeatsRepository.saveAll(seats);

        return seats;
    }

    public List<ShowEntityResponseDto> gelAllShowsInGivenDateTime(GivenShowRequestDto givenShowRequestDto){
        int id= givenShowRequestDto.getMovieId();

        MovieEntity movieEntity= movieRepository.findById(id).get();

        List<ShowEntity> showEntityList= movieEntity.getListOfShows();

        List<ShowEntityResponseDto> showList=new ArrayList<>();

        LocalDate fromDate= givenShowRequestDto.getFromDate();
        LocalDate toDate = givenShowRequestDto.getToDate();

        LocalTime fromTime= givenShowRequestDto.getFromTime();
        LocalTime toTime= givenShowRequestDto.getToTime();

        for(ShowEntity showEntity: showEntityList){

          LocalDate date = showEntity.getShowDate();
          LocalTime time = showEntity.getShowTime();

          if(date.compareTo(fromDate)>0 && date.compareTo(toDate)<0){
              if(time.compareTo(fromTime) > 0 && time.compareTo(toTime)<0){
                  ShowEntityResponseDto showEntityResponseDto= ShowConvertor.convertShowEntityToResponseDto(showEntity);
                  showList.add(showEntityResponseDto);
              }
          }
        }
        return showList;
    }
}
