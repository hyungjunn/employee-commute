package com.mycompany.employeecommute.domain.commute.history;

import com.mycompany.employeecommute.domain.employee.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class CommuteHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    private LocalDate date;
    private LocalTime arrivingTime;
    private LocalTime leavingTime;

    protected CommuteHistory() {
    }

    public CommuteHistory(Employee employee, LocalDate date, LocalTime arrivingTime) {
        this.employee = employee;
        this.date = date;
        this.arrivingTime = arrivingTime;
    }

    public LocalTime getLeavingTime() {
        return leavingTime;
    }

    public void registerLeavingTime(LocalTime leavingTime) {
        this.leavingTime = leavingTime;
    }

}
