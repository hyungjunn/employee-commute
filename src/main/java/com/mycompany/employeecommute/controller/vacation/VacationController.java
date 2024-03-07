package com.mycompany.employeecommute.controller.vacation;

import com.mycompany.employeecommute.dto.vacation.request.AnnualLeaveRegisterRequest;
import com.mycompany.employeecommute.service.vacation.VacationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VacationController {

    private final VacationService vacationService;

    public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @PostMapping("/vacation/annual")
    public void registerAnnualLeave(@RequestBody AnnualLeaveRegisterRequest request) {
        vacationService.registerAnnualLeave(request.getEmployeeId(), request.getDate());
    }
}
