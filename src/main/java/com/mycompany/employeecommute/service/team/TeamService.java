package com.mycompany.employeecommute.service.team;

import com.mycompany.employeecommute.domain.team.Team;
import com.mycompany.employeecommute.domain.team.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public void saveTeam(String name) {
        teamRepository.save(new Team(name));
    }

}
