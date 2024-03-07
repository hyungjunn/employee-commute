package com.mycompany.employeecommute.service.leave.annual;

import com.mycompany.employeecommute.domain.employee.Employee;
import com.mycompany.employeecommute.domain.employee.EmployeeRepository;
import com.mycompany.employeecommute.domain.team.Team;
import com.mycompany.employeecommute.domain.team.TeamRepository;
import com.mycompany.employeecommute.dto.leave.annual.response.AnnualLeaveGetResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class AnnualLeaveService {

    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;

    public AnnualLeaveService(EmployeeRepository employeeRepository, TeamRepository teamRepository) {
        this.employeeRepository = employeeRepository;
        this.teamRepository = teamRepository;
    }

    @Transactional
    public void registerAnnualLeave(Long employeeId, LocalDate date) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(IllegalArgumentException::new);

        Team team = teamRepository.findByName(employee.getTeamName())
                        .orElseThrow(IllegalArgumentException::new);

        //만약 요청한 date에서 현재를 뺀 값이 팀 연차 등록 기준일보다 작다면 예외를 던진다.
        if (DAYS.between(LocalDate.now(), date) < team.getVacationRegistrationCriteria()) {
            throw new IllegalArgumentException("팀의 휴가 등록 기준을 충족하지 않습니다. 다시 시도해주세요.");
        }
        employee.registerAnnualLeave(date);
    }

    @Transactional
    public AnnualLeaveGetResponse getAnnualLeave(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(IllegalArgumentException::new);

        return null;
    }
}
