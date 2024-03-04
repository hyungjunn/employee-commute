package com.mycompany.employeecommute.domain.team;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Team {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    @Column(nullable = false)
    private String name;
    private String manager;
    private int member_Count;

    protected Team() {

    }

    public Team(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)입니다. 다시 입력해주세요.", name));
        }
        this.name = name;
    }

}
