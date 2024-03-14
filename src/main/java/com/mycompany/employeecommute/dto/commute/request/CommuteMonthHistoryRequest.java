package com.mycompany.employeecommute.dto.commute.request;

import java.time.LocalDate;
import java.time.YearMonth;

public class CommuteMonthHistoryRequest {

    private final Long employeeId;
    private final YearMonth yearMonth;

    public CommuteMonthHistoryRequest(Long employeeId, YearMonth yearMonth) {
        this.employeeId = employeeId;
        this.yearMonth = yearMonth;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }
}
