package com.guess.it.core.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public class LogHandle {

    public static void printInfoLog(String currentApi, String message){
        log.info("[My Info Log] Message from api {} with {} at => {}", currentApi, message, LocalDateTime.now());
    }

    public static void printErrorLog(String currentApi, String message){
        log.error("[My Error Log] Message from api {} with {} at => {}", currentApi, message, LocalDateTime.now());
    }

    public static void printWarningLog(String currentApi, String message){
        log.warn("[My Warning Log] Message from api {} with {} at => {}", currentApi, message, LocalDateTime.now());
    }

}
