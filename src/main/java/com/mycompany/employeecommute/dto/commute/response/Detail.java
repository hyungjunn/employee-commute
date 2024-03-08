package com.mycompany.employeecommute.dto.commute.response;

import java.time.LocalDate;

public class Detail {

    private LocalDate date;
    private long workingMinutes;
    private boolean usingDayOff;

    public Detail(LocalDate date,
                  long workingMinutes,
                  boolean usingDayOff
    ) {
        this.date = date;
        this.workingMinutes = workingMinutes;
        this.usingDayOff = usingDayOff;
    }

    public LocalDate getDate() {
        return date;
    }

    public long getWorkingMinutes() {
        return workingMinutes;
    }

    public boolean isUsingDayOff() {
        return usingDayOff;
    }

}
