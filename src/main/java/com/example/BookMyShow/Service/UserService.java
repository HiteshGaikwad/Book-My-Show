package com.example.BookMyShow.Service;


import com.example.BookMyShow.Controller.UserController;
import com.example.BookMyShow.Convertors.UserConvertor;
import com.example.BookMyShow.Models.TicketEntity;
import com.example.BookMyShow.Models.UserEntity;
import com.example.BookMyShow.Repository.UserRepository;
import com.example.BookMyShow.RequestDto.UserRequestDto;
import com.example.BookMyShow.ResponseDto.UserBookedTicketResponseDto;
import com.example.BookMyShow.ResponseDto.UserEntityResponseDto;
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

    public UserEntityResponseDto getUserById(int id){
        UserEntity userEntity= userRepository.findById(id).get();

        UserEntityResponseDto userEntityResponseDto= UserConvertor.convertUserEntityToUserResponseDto(userEntity);

        return userEntityResponseDto;
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

    public UserEntityResponseDto findUserByName(String name){
        UserEntity userEntity= userRepository.findByName(name);
        UserEntityResponseDto  userEntityResponseDto =UserConvertor.convertUserEntityToUserResponseDto(userEntity);
        return userEntityResponseDto;
    }


    public List<UserEntityResponseDto> findAllUsers(){
        List<UserEntity> userEntityList=new ArrayList<>();

        userEntityList= userRepository.findAll();

        List<UserEntityResponseDto> userList=new ArrayList<>();

        for(UserEntity user: userEntityList){

            UserEntityResponseDto userResponseDto= UserConvertor.convertUserEntityToUserResponseDto(user);
            userList.add(userResponseDto);
        }

        return userList;
    }
}
