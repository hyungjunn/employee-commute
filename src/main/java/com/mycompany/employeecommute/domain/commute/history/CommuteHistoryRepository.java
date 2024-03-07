package com.mycompany.employeecommute.domain.commute.history;

import com.mycompany.employeecommute.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CommuteHistoryRepository extends JpaRepository<CommuteHistory, Long> {
    Optional<CommuteHistory> findByEmployeeAndDate(Employee employee, LocalDate date);
    boolean existsByEmployeeAndDate(Employee employee, LocalDate date);

}
