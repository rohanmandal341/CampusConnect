package com.Teams.CampusConnect.repository;

import com.Teams.CampusConnect.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    boolean existsByEmail(String email);
}
