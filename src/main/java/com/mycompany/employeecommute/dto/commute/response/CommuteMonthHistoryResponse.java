package com.mycompany.employeecommute.dto.commute.response;

import java.util.List;

public class CommuteMonthHistoryResponse {

    private List<Detail> detail;
    private long sum;

    public CommuteMonthHistoryResponse(List<Detail> detail, long sum) {
        this.detail = detail;
        this.sum = sum;
    }

    public List<Detail> getDetail() {
        return detail;
    }

    public long getSum() {
        return sum;
    }
}
