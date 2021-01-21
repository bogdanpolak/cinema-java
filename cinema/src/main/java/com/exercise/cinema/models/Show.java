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

    public String getShowId() {
        return showId;
    }

    public void setShowId(String showId) {
        this.showId = showId;
    }

    public int getMoveId() {
        return moveId;
    }

    public void setMoveId(int moveId) {
        this.moveId = moveId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }
}
