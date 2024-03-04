package com.mycompany.employeecommute.controller.team;

import com.mycompany.employeecommute.dto.team.TeamSaveRequest;
import com.mycompany.employeecommute.service.team.TeamService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/team")
    public void saveTeam(@RequestBody TeamSaveRequest request) {
        teamService.saveTeam(request.getName());
    }


}
