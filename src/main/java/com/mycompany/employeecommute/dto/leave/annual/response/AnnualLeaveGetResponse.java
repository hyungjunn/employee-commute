package com.mycompany.employeecommute.dto.leave.annual.response;

import com.mycompany.employeecommute.domain.vacation.Vacation;

import java.time.LocalDate;

public class AnnualLeaveGetResponse {

    private final LocalDate date;

    public AnnualLeaveGetResponse(Vacation vacation) {
        this.date = vacation.getDate();
    }

    public LocalDate getDate() {
        return date;
    }
}
