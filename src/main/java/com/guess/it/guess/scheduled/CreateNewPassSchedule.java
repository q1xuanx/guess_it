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
            LogHandle.printInfoLog("init", "Password generated successfully");
        }else {
            LogHandle.printWarningLog("init", "Password had generated, not generated password");
        }
    }
    @PostConstruct
    public void init() {
        LogHandle.printInfoLog("init", "Application start");
        LogHandle.printInfoLog("init", "Start Gen Password at the first time");
        boolean saved = exactPasswordService.savePassword();
        if (saved) {
            LogHandle.printInfoLog("init", "Password generated successfully");
        }else {
            LogHandle.printWarningLog("init", "Password had generated, not generated password");
        }
    }
}

