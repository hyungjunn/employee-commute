package com.mycompany.employeecommute.domain.vacation;

import com.mycompany.employeecommute.domain.employee.Employee;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

import static com.mycompany.employeecommute.domain.vacation.VacationType.*;

@Entity
public class Vacation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VacationType vacationType;

    private LocalDate date;

    protected Vacation() {
    }

    public Vacation(Employee employee, LocalDate date) {
        this.employee = employee;
        this.vacationType = ANNUAL_LEAVE;
        this.date = date;
    }

}
