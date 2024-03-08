package com.mycompany.employeecommute.service.commute;

import com.mycompany.employeecommute.domain.commute.history.CommuteHistory;
import com.mycompany.employeecommute.domain.commute.history.CommuteHistoryRepository;
import com.mycompany.employeecommute.domain.employee.Employee;
import com.mycompany.employeecommute.domain.employee.EmployeeRepository;
import com.mycompany.employeecommute.dto.commute.response.CommuteMonthHistoryResponse;
import com.mycompany.employeecommute.dto.commute.response.Detail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;

@Service
public class CommuteService {

    private final CommuteHistoryRepository commuteHistoryRepository;
    private final EmployeeRepository employeeRepository;

    public CommuteService(CommuteHistoryRepository commuteHistoryRepository, EmployeeRepository employeeRepository) {
        this.commuteHistoryRepository = commuteHistoryRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public void saveCommuteHistory(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(IllegalArgumentException::new);

        if (commuteHistoryRepository.existsByEmployeeAndDate(employee, LocalDate.now())) {
            throw new IllegalArgumentException(String.format("employee(%s)은(는) 이미 출근을 했습니다.", employee.getName()));
        }

        employee.arrive();
    }

    @Transactional
    public void updateCommuteHistory(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(IllegalArgumentException::new);

        CommuteHistory commuteHistory = commuteHistoryRepository.findByEmployeeAndDate(employee, LocalDate.now())
                .orElseThrow(IllegalArgumentException::new);

        if (commuteHistory.leavingTime() != null) {
            throw new IllegalArgumentException("퇴근 상태입니다. 출근을 먼저 해주세요.");
        }

        commuteHistory.registerLeavingTime(LocalTime.now());
    }

    @Transactional
    public CommuteMonthHistoryResponse getCommuteMonthHistory(Long employeeId, YearMonth yearMonth) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(IllegalArgumentException::new);

        LocalDate firstOfMonth = yearMonth.atDay(1);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();

        //요청한 년월(yearMonth)에 해당하는 해당 직원의 근무이력들을 가져온다.
        List<CommuteHistory> historiesOfMonth = commuteHistoryRepository
                .findByEmployeeAndDateBetween(employee, firstOfMonth, endOfMonth);

        List<Detail> details = getDetails(historiesOfMonth); // todo: 퇴근을 안한 상태로 getCommuteMonthHistory를 호출하면 N.P.E가 발생한다.

        long sum = getSum(details);

        return new CommuteMonthHistoryResponse(details, sum);
    }

    private static List<Detail> getDetails(List<CommuteHistory> historiesOfMonth) {
        return historiesOfMonth.stream()
                .map(CommuteService::applyHistoryDetail)
                .toList();
    }

    private static Detail applyHistoryDetail(CommuteHistory history) {
        // 연차를 쓴 날에는 근무시간을 0, usingDayOff를 true로 반환한다.
        // todo: 연차를 쓴 날을 표현하는 방법이 맞는지 고민해볼 것
        if (history.leavingTime() == null) {
            return new Detail(history.getDate(), 0, true);
        }

        //근무일에서 퇴근 시간과 출근 시간의 차이를 분으로 환산한다.
        return new Detail(history.getDate(),
                Duration.between(history.arrivingTime(), history.leavingTime()).toMinutes(),
                false);
    }

    private static long getSum(List<Detail> details) {
        return details.stream()
                .mapToLong(Detail::getWorkingMinutes)
                .sum();
    }

}
