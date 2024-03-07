package com.mycompany.employeecommute.dto.leave.annual.response;

import java.time.LocalDate;

public class AnnualLeaveGetResponse {

    private LocalDate date;

    public AnnualLeaveGetResponse(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }
}
