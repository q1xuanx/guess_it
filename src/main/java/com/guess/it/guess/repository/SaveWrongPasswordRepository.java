package com.guess.it.guess.repository;


import com.guess.it.guess.model.WrongGuess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaveWrongPasswordRepository extends JpaRepository<WrongGuess, Long> {
}
