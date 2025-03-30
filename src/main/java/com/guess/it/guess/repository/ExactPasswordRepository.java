package com.guess.it.guess.repository;

import com.guess.it.guess.model.ExactPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ExactPasswordRepository extends JpaRepository<ExactPassword, Integer> {
    ExactPassword findByPassword(String password);
    @Query("SELECT e FROM ExactPassword e WHERE e.timeGenerated BETWEEN :start AND :end")
    ExactPassword findByTimeGenerated(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
