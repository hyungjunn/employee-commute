package com.mycompany.employeecommute.service.employee;

import com.mycompany.employeecommute.domain.employee.Employee;
import com.mycompany.employeecommute.domain.employee.EmployeeRepository;
import com.mycompany.employeecommute.dto.employee.EmployeeSaveRequest;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void saveEmployee(EmployeeSaveRequest request) {
        employeeRepository.save(new Employee(
                request.getName(),
                request.getRole(),
                request.getWorkStartDate(),
                request.getBirthday()
                )
        );
    }

}
