package com.mycompany.employeecommute.service.vacation;

import com.mycompany.employeecommute.domain.employee.Employee;
import com.mycompany.employeecommute.domain.employee.EmployeeRepository;
import com.mycompany.employeecommute.domain.vacation.VacationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class VacationService {

    private final VacationRepository vacationRepository;
    private final EmployeeRepository employeeRepository;

    public VacationService(VacationRepository vacationRepository, EmployeeRepository employeeRepository) {
        this.vacationRepository = vacationRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public void registerAnnualLeave(Long employeeId, LocalDate date) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(IllegalArgumentException::new);

        employee.registerAnnualLeave(date);
    }

}
