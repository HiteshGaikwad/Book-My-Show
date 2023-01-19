package com.example.BookMyShow.Repository;

import com.example.BookMyShow.Models.TheatreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheatreRepository extends JpaRepository<TheatreEntity,Integer> {


}
