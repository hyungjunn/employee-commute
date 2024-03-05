package com.mycompany.employeecommute.domain.employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

import static com.mycompany.employeecommute.domain.employee.Role.MANAGER;
import static com.mycompany.employeecommute.domain.employee.Role.MEMBER;

@Entity
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    @Column(nullable = false)
    private String name;
    private String teamName;
    @Column(nullable = false) @Enumerated(EnumType.STRING)
    private Role role;
    @Column(nullable = false)
    private LocalDate birthday;
    @Column(nullable = false)
    private LocalDate workStartDate;

    protected Employee() {

    }

    public Employee(String name, Role role, LocalDate birthday, LocalDate workStartDate) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)입니다. 다시 입력해주세요.", name));
        }

        this.name = name;
        this.role = role;
        this.birthday = birthday;
        this.workStartDate = workStartDate;
    }

    public String getName() {
        return name;
    }

    public String getTeamName() {
        return teamName;
    }

    public Role getRole() {
        return role;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public LocalDate getWorkStartDate() {
        return workStartDate;
    }
}
