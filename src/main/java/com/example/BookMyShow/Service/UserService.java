package com.example.BookMyShow.Service;


import com.example.BookMyShow.Controller.UserController;
import com.example.BookMyShow.Convertors.UserConvertor;
import com.example.BookMyShow.Models.TicketEntity;
import com.example.BookMyShow.Models.UserEntity;
import com.example.BookMyShow.Repository.UserRepository;
import com.example.BookMyShow.RequestDto.UserRequestDto;
import com.example.BookMyShow.ResponseDto.UserBookedTicketResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void createUser(UserRequestDto userRequestDto){

        UserEntity user= UserConvertor.convertRequestDtoToUser(userRequestDto);

        userRepository.save(user);
    }

    public UserEntity getUserById(int id){
        return userRepository.findById(id).get();
    }

    public List<UserBookedTicketResponseDto> getAllTicketBookedByUser(int userId){

        UserEntity userEntity= userRepository.findById(userId).get();

        List<TicketEntity> ticketEntityList= userEntity.getTicket();

        List<UserBookedTicketResponseDto> bookedTicketList=new ArrayList<>();

        for(TicketEntity ticket: ticketEntityList){

            UserBookedTicketResponseDto userBookedTicketResponseDto=
                    UserConvertor.convertToUserBookedTicket(ticket);

            bookedTicketList.add(userBookedTicketResponseDto);
        }
        return bookedTicketList;
    }

    public UserEntity findUserByName(String name){
        UserEntity userEntity= userRepository.findByName(name);
        return userEntity;
    }
}
