package com.exercise.cinema.models;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "showtimes")
public class Show {
    @Id
    @Column(name = "showid")
    String  showId;
    @Column(name = "movieid")
    int moveId;
    @Column(name = "roomid")
    int roomId;
    Date start;
}
