package com.mycompany.employeecommute.domain.employee.work_history;

import com.mycompany.employeecommute.domain.employee.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class EmployeeWorkHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @ManyToOne
    private Employee employee;

    private boolean isWork;

    private LocalDateTime date;

    protected EmployeeWorkHistory() {

    }

    public EmployeeWorkHistory(Employee employee, boolean isWork, LocalDateTime date) {
        this.employee = employee;
        this.isWork = isWork;
        this.date = date;
    }
}
