package com.mycompany.employeecommute.dto.employee.response;

import com.mycompany.employeecommute.domain.employee.Employee;
import com.mycompany.employeecommute.domain.employee.Role;

import java.time.LocalDate;

public class EmployeeResponse {

    private final String name;
    private final String teamName;
    private final Role role;
    private final LocalDate birthday;
    private final LocalDate workStartDate;

    public EmployeeResponse(Employee employee) {
        this.name = employee.getName();
        this.teamName = employee.getTeamName();
        this.role = employee.getRole();
        this.birthday = employee.getBirthday();
        this.workStartDate = employee.getWorkStartDate();
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
