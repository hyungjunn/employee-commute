package com.mycompany.employeecommute.domain.vacation.policy.annual_leave;

public enum AnnualLeavePolicy {

    HIRED_THIS_YEAR_ANNUAL_LEAVE(11),
    ANNUAL_LEAVE(15);

    private final int days;

    AnnualLeavePolicy(int days) {
        this.days = days;
    }

    public int getDays() {
        return days;
    }

}
