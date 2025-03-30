package com.guess.it.guess.service;

import com.guess.it.guess.model.ExactPassword;
import com.guess.it.guess.repository.ExactPasswordRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ExactPasswordService {
    private final ExactPasswordRepository exactPasswordRepository;
    private char[] specialCharacters = new char[]{'!', '@', '#', '$', '%', '^', '&', '*'};

    @Transactional
    public boolean savePassword(){
        LocalDate currentDate = LocalDate.now();
        LocalDateTime startDate = currentDate.atStartOfDay();
        LocalDateTime endDate = currentDate.atTime(23, 59, 59, 999999999);
        ExactPassword existToday = exactPasswordRepository.findByTimeGenerated(startDate, endDate);
        if (existToday != null){
            return false;
        }
        ExactPassword exactPassword = ExactPassword.builder()
                .password(generatePassword())
                .timeGenerated(LocalDateTime.now())
                .build();
        exactPasswordRepository.save(exactPassword);
        return true;
    }

    private LocalDateTime getCurrentDate(){
        String current = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        return LocalDateTime.parse(current);
    }

    public String generatePassword(){
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
