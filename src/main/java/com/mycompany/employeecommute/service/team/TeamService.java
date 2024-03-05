package com.mycompany.employeecommute.service.team;

import com.mycompany.employeecommute.domain.team.Team;
import com.mycompany.employeecommute.domain.team.TeamRepository;
import com.mycompany.employeecommute.dto.team.response.TeamResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Transactional
    public void saveTeam(String name) {
        teamRepository.save(new Team(name));
    }

    @Transactional
    public List<TeamResponse> getTeam() {
        return teamRepository.findAll().stream()
                .map(TeamResponse::new)
                .collect(Collectors.toList());
    }

}
