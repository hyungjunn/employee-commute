package com.mycompany.employeecommute.dto.commute.response;

import java.time.LocalDate;

public class Detail {

    private LocalDate date;
    private long workingMinutes;

    public Detail(LocalDate date, long workingMinutes) {
        this.date = date;
        this.workingMinutes = workingMinutes;
    }

    public LocalDate getDate() {
        return date;
    }

    public long getWorkingMinutes() {
        return workingMinutes;
    }
}
