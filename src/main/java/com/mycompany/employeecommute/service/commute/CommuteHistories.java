package com.mycompany.employeecommute.service.commute;

import com.mycompany.employeecommute.domain.commute.history.CommuteHistory;
import com.mycompany.employeecommute.dto.commute.response.Detail;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class CommuteHistories {

    private final List<CommuteHistory> commuteHistoryList;
    private static final long WORKING_TIME_WHEN_VACATION = 0;
    private final static boolean USING_DAY_OFF = true;

    public CommuteHistories(List<CommuteHistory> commuteHistoryList) {
        this.commuteHistoryList = commuteHistoryList;
    }

    public List<Detail> getDetails() {
        return commuteHistoryList.stream()
                .map(this::applyHistoryDetail)
                .collect(Collectors.toList());
    }

    private Detail applyHistoryDetail(CommuteHistory history) {
        if (history.isVacationDay()) {
            return new Detail(history.getDate(), WORKING_TIME_WHEN_VACATION, USING_DAY_OFF);
        }

        //근무일에서 퇴근 시간과 출근 시간의 차이를 분으로 환산한다.
        return new Detail(history.getDate(),
                Duration.between(
                        history.arrivingTime(),
                        history.leavingTime()).toMinutes(),
                false);
    }

}
