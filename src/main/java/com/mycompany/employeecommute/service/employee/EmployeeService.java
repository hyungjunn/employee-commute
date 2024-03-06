package com.mycompany.employeecommute.service.employee;

import com.mycompany.employeecommute.domain.employee.Employee;
import com.mycompany.employeecommute.domain.employee.EmployeeRepository;
import com.mycompany.employeecommute.domain.employee.work_history.EmployeeWorkHistory;
import com.mycompany.employeecommute.domain.employee.work_history.EmployeeWorkHistoryRepository;
import com.mycompany.employeecommute.dto.employee.request.EmployeeSaveRequest;
import com.mycompany.employeecommute.dto.employee.request.EmployeeWorkHistoryRequest;
import com.mycompany.employeecommute.dto.employee.response.EmployeeResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeWorkHistoryRepository employeeWorkHistoryRepository;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeWorkHistoryRepository employeeWorkHistoryRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeWorkHistoryRepository = employeeWorkHistoryRepository;
    }

    @Transactional
    public void saveEmployee(EmployeeSaveRequest request) {
        employeeRepository.save(new Employee(
                request.getName(),
                request.getRole(),
                request.getWorkStartDate(),
                request.getBirthday()
                )
        );
    }

    @Transactional
    public List<EmployeeResponse> getEmployee() {
        return employeeRepository.findAll().stream()
                .map(EmployeeResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveWorkHistory(EmployeeWorkHistoryRequest request) {
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(IllegalArgumentException::new);
        System.out.println(employee.getId());
        System.out.println(employee.getName());
        //employeeWorkHistoryRepository.save(new EmployeeWorkHistory(employee.getId(), true, LocalDateTime.now()));
        employee.hasWork();
    }

}
