package com.mycompany.employeecommute.controller.employee;

import com.mycompany.employeecommute.dto.employee.request.EmployeeSaveRequest;
import com.mycompany.employeecommute.dto.employee.response.EmployeeResponse;
import com.mycompany.employeecommute.service.employee.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/employee")
    public List<EmployeeResponse> getEmployee() {
        return employeeService.getEmployee();
    }

}
