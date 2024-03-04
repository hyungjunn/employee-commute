package com.mycompany.employeecommute.controller.employee;

import com.mycompany.employeecommute.dto.employee.EmployeeSaveRequest;
import com.mycompany.employeecommute.service.employee.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employee")
    public void saveEmployee(@RequestBody EmployeeSaveRequest request) {
        employeeService.saveEmployee(request);
    }
}
