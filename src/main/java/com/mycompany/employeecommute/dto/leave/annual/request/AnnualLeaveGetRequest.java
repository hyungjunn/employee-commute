package com.mycompany.employeecommute.dto.leave.annual.request;

public class AnnualLeaveGetRequest {

    private final Long employeeId;

    public AnnualLeaveGetRequest(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

}
