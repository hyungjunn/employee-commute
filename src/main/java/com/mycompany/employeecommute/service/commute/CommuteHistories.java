package com.mycompany.employeecommute.service.commute;

import com.mycompany.employeecommute.domain.commute.history.CommuteHistory;
import com.mycompany.employeecommute.dto.commute.response.Detail;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class CommuteHistories {

    private List<CommuteHistory> commuteHistoryList;

    public CommuteHistories(List<CommuteHistory> commuteHistoryList) {
        this.commuteHistoryList = commuteHistoryList;
    }

    public List<Detail> getDetails() {
        return commuteHistoryList.stream()
                .map(this::applyHistoryDetail)
                .collect(Collectors.toList());
    }

    private Detail applyHistoryDetail(CommuteHistory history) {
        // 연차를 쓴 날에는 근무시간을 0, usingDayOff를 true로 반환한다.
        // todo: 연차를 쓴 날을 표현하는 방법이 맞는지 고민해볼 것
        if (history.leavingTime() == history.arrivingTime()) {
            return new Detail(history.getDate(), 0, true);
        }

        //근무일에서 퇴근 시간과 출근 시간의 차이를 분으로 환산한다.
        return new Detail(history.getDate(),
                Duration.between(history.arrivingTime(), history.leavingTime()).toMinutes(),
                false);
    }

}
