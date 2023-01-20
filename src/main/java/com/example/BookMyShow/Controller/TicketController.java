package com.example.BookMyShow.Controller;


import com.example.BookMyShow.RequestDto.BookTicketRequestDto;
import com.example.BookMyShow.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/book")
    public ResponseEntity<String> bookTicket(@RequestBody()BookTicketRequestDto bookTicketRequestDto){

        try {
            ticketService.bookTicket(bookTicketRequestDto);
            return new ResponseEntity<>("Ticket booked successfully", HttpStatus.CREATED);
        } catch(Exception e){
            return new ResponseEntity<>("Requested seats are not available",HttpStatus.NOT_FOUND) ;
        }
    }

    @DeleteMapping("/cancel-ticket")
    public ResponseEntity<String> cancelTicket(@RequestParam("userId") int userId, @RequestParam("ticketId") int ticketId){
        int deductedMoney= ticketService.cancelTicket(userId,ticketId);
        return new ResponseEntity<>("Ticket cancelled successfully and Rs."+" "+ deductedMoney +" "+ "of your amount got deducted",HttpStatus.ACCEPTED);
    }
}


/*
*
 */