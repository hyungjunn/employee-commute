package com.mycompany.employeecommute.controller.commute;

import com.mycompany.employeecommute.dto.commute.request.CommuteHistoryCreateRequest;
import com.mycompany.employeecommute.dto.commute.request.CommuteHistoryUpdateRequest;
import com.mycompany.employeecommute.dto.commute.request.CommuteMonthHistoryRequest;
import com.mycompany.employeecommute.dto.commute.response.CommuteMonthHistoryResponse;
import com.mycompany.employeecommute.service.commute.CommuteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommuteController {

    private final CommuteService commuteService;

    public CommuteController(CommuteService commuteService) {
        this.commuteService = commuteService;
    }

    @PostMapping("/commute/arrive")
    public void saveCommuteHistory(@RequestBody CommuteHistoryCreateRequest request) {
        commuteService.saveCommuteHistory(request.getEmployeeId());
    }

    @PutMapping("/commute/leave")
    public void updateCommuteHistory(@RequestBody CommuteHistoryUpdateRequest request) {
        commuteService.updateCommuteHistory(request.getEmployeeId());
    }

    @GetMapping("/commute/history/month")
    public CommuteMonthHistoryResponse getCommuteMonthHistory(CommuteMonthHistoryRequest request) {
        return commuteService.getCommuteMonthHistory(request.getEmployeeId(), request.getYearMonth());
    }

}
