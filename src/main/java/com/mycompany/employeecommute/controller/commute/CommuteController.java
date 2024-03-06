package com.mycompany.employeecommute.controller.commute;

import com.mycompany.employeecommute.dto.commute.request.CommuteHistoryCreateRequest;
import com.mycompany.employeecommute.dto.commute.request.CommuteHistoryUpdateRequest;
import com.mycompany.employeecommute.service.commute.CommuteService;
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
        commuteService.saveCommuteHistory(request);
    }

    @PutMapping("/commute/leave")
    public void updateCommuteHistory(@RequestBody CommuteHistoryUpdateRequest request) {
        commuteService.updateCommuteHistory(request);
    }
}
