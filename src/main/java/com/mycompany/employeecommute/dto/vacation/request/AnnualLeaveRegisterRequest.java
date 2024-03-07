package com.mycompany.employeecommute.dto.vacation.request;

import java.time.LocalDate;

public class AnnualLeaveRegisterRequest {

    private Long employeeId;
    private LocalDate date;

    public Long getEmployeeId() {
        return employeeId;
    }

    public LocalDate getDate() {
        return date;
    }
}
