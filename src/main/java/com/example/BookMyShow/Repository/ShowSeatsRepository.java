package com.example.BookMyShow.Repository;

import com.example.BookMyShow.Models.ShowSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatsRepository extends JpaRepository<ShowSeatEntity,Integer> {

}
