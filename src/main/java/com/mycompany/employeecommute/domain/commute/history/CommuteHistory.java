package com.mycompany.employeecommute.domain.commute.history;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class CommuteHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    private Long userId;
    private LocalDate date;
    private LocalTime arrivingTime;
    private LocalTime leavingTime;

    protected CommuteHistory() {
    }

    public CommuteHistory(Long userId, LocalDate date, LocalTime arrivingTime) {
        this.userId = userId;
        this.date = date;
        this.arrivingTime = arrivingTime;
    }
}
