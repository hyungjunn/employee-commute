package com.mycompany.employeecommute.service.commute;

import com.mycompany.employeecommute.domain.commute.history.CommuteHistory;
import com.mycompany.employeecommute.domain.commute.history.CommuteHistoryRepository;
import com.mycompany.employeecommute.domain.employee.Employee;
import com.mycompany.employeecommute.domain.employee.EmployeeRepository;
import com.mycompany.employeecommute.dto.commute.request.CommuteHistoryCreateRequest;
import com.mycompany.employeecommute.dto.commute.request.CommuteHistoryUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class CommuteService {

    private final CommuteHistoryRepository commuteHistoryRepository;
    private final EmployeeRepository employeeRepository;

    public CommuteService(CommuteHistoryRepository commuteHistoryRepository, EmployeeRepository employeeRepository) {
        this.commuteHistoryRepository = commuteHistoryRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public void saveCommuteHistory(CommuteHistoryCreateRequest request) {
        //1.직원정보를 가져온다
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(IllegalArgumentException::new);
        //todo: 오늘날에 출근을 했다면 에러를 던진다
        if (commuteHistoryRepository.existsByIdAndDate(employee.getId(), LocalDate.now())) {
            throw new IllegalArgumentException(String.format("employee(%s)은(는) 이미 출근을 했습니다.", employee.getName()));
        }
        //2.직원정보를 토대로 출근 이력을 저장한다
        //commuteHistoryRepository.save(new CommuteHistory(employee));
        employee.arrive();
    }

    @Transactional
    public void updateCommuteHistory(CommuteHistoryUpdateRequest request) {
        //1.직원정보를 가져온다.
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(IllegalArgumentException::new);

        //2.직원 출근 이력을 가지고 온다
        CommuteHistory commuteHistory = commuteHistoryRepository.findByEmployeeAndDate(employee, LocalDate.now())
                .orElseThrow(IllegalArgumentException::new);

        //3.만약 퇴근 이력이 있다면 예외를 던진다
        if (commuteHistory.getLeavingTime() != null) {
            throw new IllegalArgumentException("퇴근 상태입니다. 출근을 먼저 해주세요.");
        }

        //4.퇴근 이력을 현재 시간으로 넣어준다
        //commuteHistoryRepository.save(commuteHistory);
        commuteHistory.registerLeavingTime(LocalTime.now());
    }

}
