package com.guess.it.guess.service;

import com.guess.it.core.dto.ApiResponse;
import com.guess.it.core.utils.DateHandle;
import com.guess.it.core.utils.LogHandle;
import com.guess.it.guess.model.ExactPassword;
import com.guess.it.guess.model.WrongGuess;
import com.guess.it.guess.repository.ExactPasswordRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExactPasswordService {
    private final ExactPasswordRepository exactPasswordRepository;
    private final WrongGuessService wrongGuessService;
    private char[] specialCharacters = new char[]{'!', '@', '#', '$', '%', '^', '&', '*'};

    public ApiResponse<Boolean> guessPassword(String password) {
        Pair<LocalDateTime, LocalDateTime> getEndAndStart = DateHandle.getStartAndEndDate();
        ExactPassword exactPassword = exactPasswordRepository.findByTimeGenerated(getEndAndStart.getFirst(), getEndAndStart.getSecond());
        if (exactPassword == null || exactPassword.isGuessed()) {
            LogHandle.printWarningLog("guessPassword", "exact password is already guessed");
            return new ApiResponse<>(400, "Error when generate password, please try again ! \n Or password is guessed today, please try tomorrow", false);
        }
        if (exactPassword.getPassword().equals(password)) {
            exactPassword.setGuessed(true);
            exactPasswordRepository.save(exactPassword);
            LogHandle.printWarningLog("guessPassword", "Exact password guessed at: " + LocalDateTime.now());
            return new ApiResponse<>(200, "Successfully ! your guess is correct", true);
        }
        WrongGuess wrongGuess = WrongGuess.builder()
                .guess(password)
                .timeGuess(LocalDateTime.now())
                .build();
        wrongGuessService.save(wrongGuess);
        LogHandle.printInfoLog("guessPassword", "Guess wrong password at: " + LocalDateTime.now());
        return new ApiResponse<>(400, "Password not correct", false);
    }

    @Transactional
    public boolean savePassword(){
        Pair<LocalDateTime, LocalDateTime> startAndEndDate = DateHandle.getStartAndEndDate();
        ExactPassword existToday = exactPasswordRepository.findByTimeGenerated(startAndEndDate.getFirst(), startAndEndDate.getSecond());
        if (existToday != null){
            LogHandle.printWarningLog("savePassword", "exact password is already guessed || password had generated");
            return false;
        }
        ExactPassword exactPassword = ExactPassword.builder()
                .password(generatePassword())
                .timeGenerated(LocalDateTime.now())
                .build();
        exactPasswordRepository.save(exactPassword);
        LogHandle.printInfoLog("savePassword", "Exact password saved");
        return true;
    }

    private String generatePassword(){
        String [] listResult = new String[3];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String now = LocalDateTime.now().format(formatter);
        Random rand = new Random();

        listResult[0] = now;
        listResult[1] = String.valueOf(specialCharacters[rand.nextInt(specialCharacters.length)]);
        listResult[2] = String.valueOf(specialCharacters[rand.nextInt(specialCharacters.length)]);

        for (int i = listResult.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            String temp = listResult[i];
            listResult[i] = listResult[j];
            listResult[j] = temp;
        }
        return String.join("", listResult);
    }

}
