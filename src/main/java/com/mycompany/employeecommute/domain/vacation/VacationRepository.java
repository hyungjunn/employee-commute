package com.mycompany.employeecommute.domain.vacation;

import com.mycompany.employeecommute.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VacationRepository extends JpaRepository<Vacation, Long> {
    boolean existsByEmployeeAndDate(Employee employee, LocalDate date);
    List<Vacation> findByEmployeeAndDateGreaterThan(Employee employee, LocalDate date);
}
