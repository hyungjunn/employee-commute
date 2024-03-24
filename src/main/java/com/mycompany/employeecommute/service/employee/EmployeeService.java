package com.mycompany.employeecommute.service.employee;

import com.mycompany.employeecommute.domain.employee.Employee;
import com.mycompany.employeecommute.domain.employee.EmployeeRepository;
import com.mycompany.employeecommute.dto.employee.request.EmployeeSaveRequest;
import com.mycompany.employeecommute.dto.employee.response.EmployeeResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public void saveEmployee(EmployeeSaveRequest request) {
        employeeRepository.save(new Employee
                        .Builder(request.getName())
                        .role(request.getRole())
                        .birthday(request.getBirthday())
                        .workStartDate(request.getWorkStartDate())
                        .build());
    }

    @Transactional
    public List<EmployeeResponse> getEmployee() {
        return employeeRepository.findAll().stream()
                .map(EmployeeResponse::new)
                .collect(Collectors.toList());
    }

}
