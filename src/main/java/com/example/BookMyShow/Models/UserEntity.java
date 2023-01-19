package com.example.BookMyShow.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="user")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

     private  String name;

     private String mobile;

     private int age;

     @CreationTimestamp
     @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdDate;

     @UpdateTimestamp
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updatedOn;


     @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<TicketEntity> ticket;



}
