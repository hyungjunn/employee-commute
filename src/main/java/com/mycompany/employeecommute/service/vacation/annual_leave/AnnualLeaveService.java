package com.mycompany.employeecommute.service.vacation.annual_leave;

import com.mycompany.employeecommute.domain.employee.Employee;
import com.mycompany.employeecommute.domain.employee.EmployeeRepository;
import com.mycompany.employeecommute.domain.team.Team;
import com.mycompany.employeecommute.domain.team.TeamRepository;
import com.mycompany.employeecommute.domain.vacation.Vacation;
import com.mycompany.employeecommute.domain.vacation.VacationRepository;
import com.mycompany.employeecommute.dto.leave.annual.response.AnnualLeaveGetResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class AnnualLeaveService {

    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;
    private final VacationRepository vacationRepository;

    public AnnualLeaveService(EmployeeRepository employeeRepository,
                              TeamRepository teamRepository,
                              VacationRepository vacationRepository
    ) {
        this.employeeRepository = employeeRepository;
        this.teamRepository = teamRepository;
        this.vacationRepository = vacationRepository;
    }

    @Transactional
    public void registerAnnualLeave(Long employeeId, LocalDate date) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(IllegalArgumentException::new);

        Team team = teamRepository.findByName(employee.getTeamName())
                        .orElseThrow(IllegalArgumentException::new);

        if (vacationRepository.existsByEmployeeAndDate(employee, date)) {
            throw new IllegalArgumentException("이미 등록하신 연차입니다.");
        }

        if (isNotSatisfiedTeamAnnualRegisterCondition(date, team)) {
            throw new IllegalArgumentException("팀의 연차 등록 조건을 만족하지 못합니다. 희망 연차일을 다시 등록해주세요.");
        }
        employee.registerAnnualLeave(date);
    }

    @Transactional
    public List<AnnualLeaveGetResponse> getAnnualLeave(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(IllegalArgumentException::new);
        return vacationRepository
                .findByEmployeeAndDateGreaterThan(employee, LocalDate.now())
                .stream()
                .map(AnnualLeaveGetResponse::new)
                .collect(Collectors.toList());
    }

    private static boolean isNotSatisfiedTeamAnnualRegisterCondition(LocalDate date, Team team) {
        return DAYS.between(LocalDate.now(), date) < team.getVacationRegistrationCriteria();
    }

}
