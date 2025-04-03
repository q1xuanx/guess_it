package com.guess.it.guess.repository;


import com.guess.it.guess.model.WrongGuess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaveWrongPasswordRepository extends JpaRepository<WrongGuess, Long> {
    @Query("SELECT e FROM WrongGuess as e ORDER BY e.timeGuess DESC LIMIT :limit OFFSET :offset")
    List<WrongGuess> findWrongGuessByLimitAndOffset(@Param("limit") int limit, @Param("offset") int offset);

    @Query("SELECT COUNT (*) FROM WrongGuess")
    int countWrongGuess();
}
