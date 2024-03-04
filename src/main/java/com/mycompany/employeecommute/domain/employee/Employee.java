package com.mycompany.employeecommute.domain.employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    private String name;
    private String teamName;
    @Column @Enumerated(EnumType.STRING)
    private Role role;
    @Column(nullable = false)
    private LocalDate birthday;
    private LocalDate workStartDate;

}
