package com.mycompany.employeecommute.service.commute;

import com.mycompany.employeecommute.domain.commute.history.CommuteHistory;
import com.mycompany.employeecommute.domain.commute.history.CommuteHistoryRepository;
import com.mycompany.employeecommute.domain.employee.Employee;
import com.mycompany.employeecommute.domain.employee.EmployeeRepository;
import com.mycompany.employeecommute.dto.commute.request.CommuteHistoryCreateRequest;
import org.springframework.stereotype.Service;

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

    public void saveCommuteHistory(CommuteHistoryCreateRequest request) {
        //1.직원정보를 가져온다
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(IllegalArgumentException::new);
        //2.직원정보를 토대로 출근 이력을 저장한다
        commuteHistoryRepository.save(new CommuteHistory(employee.getId(), LocalDate.now(), LocalTime.now()));
    }
}
