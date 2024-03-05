package com.mycompany.employeecommute.dto.team.response;

import com.mycompany.employeecommute.domain.team.Team;

public class TeamResponse {

    private String name;
    private String manager;
    private int memberCount;

    public TeamResponse(Team team) {
        this.name = team.getName();
        this.manager = team.getManager();
        this.memberCount = team.getMember_Count();
    }

    public String getName() {
        return name;
    }

    public String getManager() {
        return manager;
    }

    public int getMemberCount() {
        return memberCount;
    }
}
