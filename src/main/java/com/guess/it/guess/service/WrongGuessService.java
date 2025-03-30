package com.guess.it.guess.service;

import com.guess.it.core.dto.ApiResponse;
import com.guess.it.guess.model.WrongGuess;
import com.guess.it.guess.repository.SaveWrongPasswordRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WrongGuessService {
    private final SaveWrongPasswordRepository saveWrongPasswordRepository;
    @Transactional
    public void save(WrongGuess wrongGuess) {
        try {
            saveWrongPasswordRepository.save(wrongGuess);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public ApiResponse<List<WrongGuess>> getListOfWrongGuess() {
        List<WrongGuess> wrongGuessList = saveWrongPasswordRepository.findAll();
        return new ApiResponse<>(200, "Get list success", wrongGuessList);
    }
}
