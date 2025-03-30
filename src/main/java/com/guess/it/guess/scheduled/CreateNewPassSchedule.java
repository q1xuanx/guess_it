package com.guess.it.guess.scheduled;


import com.guess.it.core.utils.LogHandle;
import com.guess.it.guess.service.ExactPasswordService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Slf4j
@EnableScheduling
@Configuration
@RequiredArgsConstructor
public class CreateNewPassSchedule {
    private final ExactPasswordService exactPasswordService;

    @Scheduled(cron = "0 0 0 * * *")
    public void createNewPassword() {
        boolean saved = exactPasswordService.savePassword();
        if (saved) {
            log.info("New password created at {}", LocalDateTime.now());
        }else {
            log.warn("Today password is have created {}", LocalDateTime.now());
        }
    }

    @PostConstruct
    public void init() {
        LogHandle.printInfoLog("init", "Start application => " + LocalDateTime.now());
        LogHandle.printInfoLog("init", "Start generate password => " + LocalDateTime.now());
        boolean startGenerate = exactPasswordService.savePassword();
        if (startGenerate) {
            LogHandle.printInfoLog("init", "Generate success => " + LocalDateTime.now());
        }else {
            LogHandle.printInfoLog("init", "Password had generate => " + LocalDateTime.now());
        }
    }

}

