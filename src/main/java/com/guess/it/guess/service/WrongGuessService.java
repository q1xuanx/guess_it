package com.guess.it.guess.service;

import com.guess.it.core.dto.ApiResponse;
import com.guess.it.core.utils.PageHandle;
import com.guess.it.guess.model.WrongGuess;
import com.guess.it.guess.repository.SaveWrongPasswordRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public ApiResponse<Map<String, Object>> getListOfWrongGuess(int currentPage, int pageSize) {
        int totalItems = saveWrongPasswordRepository.countWrongGuess();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);
        if (currentPage < 1 || currentPage > totalPages) {
            currentPage = 1;
        }
        List<WrongGuess> wrongGuessList = saveWrongPasswordRepository.findWrongGuessByLimitAndOffset(pageSize, (currentPage - 1) * pageSize);
        Map<String, Object> data = PageHandle.page(totalItems, pageSize, wrongGuessList);
        return new ApiResponse<>(200, "Get list success", data);
    }
}
