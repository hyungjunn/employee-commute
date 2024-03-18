package com.mycompany.employeecommute.controller.vacation.annual_leave;

import com.mycompany.employeecommute.dto.leave.annual.request.AnnualLeaveRegisterRequest;
import com.mycompany.employeecommute.dto.leave.annual.response.AnnualLeaveGetResponse;
import com.mycompany.employeecommute.service.vacation.annual_leave.AnnualLeaveService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnnualLeaveController {

    private final AnnualLeaveService annualLeaveService;

    public AnnualLeaveController(AnnualLeaveService annualLeaveService) {
        this.annualLeaveService = annualLeaveService;
    }

    @PostMapping("/leave/annual")
    public void registerAnnualLeave(@RequestBody AnnualLeaveRegisterRequest request) {
        annualLeaveService.registerAnnualLeave(request.getEmployeeId(), request.getDate());
    }

    @GetMapping("/leave/annual")
    public List<AnnualLeaveGetResponse> getAnnualLeave(long employeeId) {
        return annualLeaveService.getAnnualLeave(employeeId);
    }

}
