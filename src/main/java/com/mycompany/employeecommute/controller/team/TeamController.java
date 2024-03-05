package com.mycompany.employeecommute.controller.team;

import com.mycompany.employeecommute.dto.team.request.TeamSaveRequest;
import com.mycompany.employeecommute.dto.team.response.TeamResponse;
import com.mycompany.employeecommute.service.team.TeamService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/team")
    public List<TeamResponse> getTeam() {
        return teamService.getTeam();
    }

}
