package com.mycompany.employeecommute.domain.commute.history;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommuteHistoryRepository extends JpaRepository<CommuteHistory, Long> {
}
