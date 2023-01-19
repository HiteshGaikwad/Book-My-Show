package com.example.BookMyShow.RequestDto;


import com.example.BookMyShow.Models.ShowSeatEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTicketRequestDto {

   private List<String> requestSeats;

    private int showId;

    private int userId;
}
