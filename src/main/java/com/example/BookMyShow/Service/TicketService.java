package com.example.BookMyShow.Service;


import com.example.BookMyShow.Models.ShowEntity;
import com.example.BookMyShow.Models.ShowSeatEntity;
import com.example.BookMyShow.Models.TicketEntity;
import com.example.BookMyShow.Models.UserEntity;
import com.example.BookMyShow.Repository.ShowRepository;
import com.example.BookMyShow.Repository.ShowSeatsRepository;
import com.example.BookMyShow.Repository.TicketRepository;
import com.example.BookMyShow.Repository.UserRepository;
import com.example.BookMyShow.RequestDto.BookTicketRequestDto;
import org.hibernate.type.descriptor.java.LocalDateJavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShowSeatsRepository showSeatsRepository;

    public void bookTicket(BookTicketRequestDto bookTicketRequestDto) throws Exception{


        //get the requested seats
        List<String> requestedSeats= bookTicketRequestDto.getRequestSeats();

        ShowEntity showEntity= showRepository.findById(bookTicketRequestDto.getShowId()).get();

        UserEntity userEntity=userRepository.findById(bookTicketRequestDto.getUserId()).get();

        //get the showSeats from showEntity
        List<ShowSeatEntity> showSeats= showEntity.getListOfSeats();

        List<ShowSeatEntity> bookedSeats=new ArrayList<>();

        //validate seats allocate if possible to requested seats  by user
        for(ShowSeatEntity showSeat:  showSeats){

            String seatNo= showSeat.getSeatNo();

            if(showSeat.isBooked()==false && requestedSeats.contains(seatNo)){
                bookedSeats.add(showSeat);
            }
        }

        if(bookedSeats.size() != requestedSeats.size()){
            throw new Exception("Requested Seats are not available");
        }

        //this means all requested seats are available
        TicketEntity ticketEntity=new TicketEntity();

        double totalAmount=0;
        double multiplier= showEntity.getMultiplier();

        String allotedSeats="";
        int rate=0;
        for(ShowSeatEntity bookedSeat: bookedSeats){

            bookedSeat.setBooked(true);
            bookedSeat.setBookedAt(new Date());
            bookedSeat.setTicket(ticketEntity);
            bookedSeat.setShows(showEntity);

            String seatNo= bookedSeat.getSeatNo();

            allotedSeats+=seatNo+",";

            if(seatNo.charAt(0)=='1'){
                rate=100;
            }
            else{
                rate=200;
            }

            totalAmount+= multiplier*rate;
        }

        ticketEntity.setBookedDate(new Date());
        ticketEntity.setShows(showEntity);
        ticketEntity.setAmount((int)totalAmount);
        ticketEntity.setUser(userEntity);
        ticketEntity.setBookedSeats(bookedSeats);
        ticketEntity.setAllotedSeats(allotedSeats);

        ticketRepository.save(ticketEntity);

    }

    public int cancelTicket(int userId, int ticketId) {


        //get ticket entity to be cancelled
        TicketEntity ticketEntity = ticketRepository.findById(ticketId).get();



        int totalAmount= ticketEntity.getAmount();

        int deductedMoney=(int) ((20*totalAmount)/100);

        ShowEntity showEntity= ticketEntity.getShows();

        //deleting ticket from list of ticket from showEntity

        List<TicketEntity> ticketOfShow= showEntity.getListOfTickets();
        ticketOfShow.remove(ticketEntity);
        showEntity.setListOfTickets(ticketOfShow);

        showRepository.save(showEntity);


        UserEntity userEntity = userRepository.findById(userId).get();

        //deleting ticket from list of tickets of user and updating user

        List<TicketEntity> ticketEntityList = userEntity.getTicket();
        ticketEntityList.remove(ticketEntity);
        userEntity.setTicket(ticketEntityList);


        List<ShowSeatEntity> bookedSeats = ticketEntity.getBookedSeats();

        for (ShowSeatEntity bookedSeat : bookedSeats) {

            bookedSeat.setBooked(false);
            bookedSeat.setBookedAt(null);
            bookedSeat.setTicket(null);

        }
        bookedSeats.clear();

        ticketRepository.delete(ticketEntity);
        userRepository.save(userEntity);

        return deductedMoney;
    }


}
