package com.mycompany.employeecommute.domain.employee;

import com.mycompany.employeecommute.domain.commute.history.CommuteHistory;
import com.mycompany.employeecommute.domain.vacation.Vacation;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.mycompany.employeecommute.domain.vacation.policy.annual_leave.AnnualLeavePolicy.ANNUAL_LEAVE;
import static com.mycompany.employeecommute.domain.vacation.policy.annual_leave.AnnualLeavePolicy.HIRED_THIS_YEAR_ANNUAL_LEAVE;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<CommuteHistory> commuteHistories = new ArrayList<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private final List<Vacation> vacations = new ArrayList<>();

    @Column(nullable = false)
    private String name;

    private String teamName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false)
    private LocalDate workStartDate;

    protected Employee() {

    }

    public Employee(String name, Role role, LocalDate birthday, LocalDate workStartDate) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)입니다. 다시 입력해주세요.", name));
        }

        this.name = name;
        this.role = role;
        this.birthday = birthday;
        this.workStartDate = workStartDate;
    }

    public String getName() {
        return name;
    }

    public String getTeamName() {
        return teamName;
    }

    public Role getRole() {
        return role;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public LocalDate getWorkStartDate() {
        return workStartDate;
    }

    public void arrive() {
        this.commuteHistories.add(new CommuteHistory(this));
    }

    public void registerAnnualLeave(LocalDate date) {
        if (isNotSatisfiedHiredThisYearCondition()) {
            throw new IllegalArgumentException("올해 등록할 수 있는 연차를 모두 사용하셨습니다.");
        }

        if (isNotSatisfiedHiredNotThisYearCondition()) {
            throw new IllegalArgumentException("올해 등록할 수 있는 연차를 모두 사용하셨습니다.");
        }
        this.commuteHistories.add(new CommuteHistory(this, date));
        this.vacations.add(new Vacation(this, date));
    }

    private boolean isNotSatisfiedHiredThisYearCondition() {
        return hasHiredThisYear() && this.vacations.size() >= HIRED_THIS_YEAR_ANNUAL_LEAVE.getDays();
    }

    private boolean hasHiredThisYear() {
        return this.workStartDate.getYear() == LocalDate.now().getYear();
    }

    private boolean isNotSatisfiedHiredNotThisYearCondition() {
        return !hasHiredThisYear() && this.vacations.size() >= ANNUAL_LEAVE.getDays();
    }

}
