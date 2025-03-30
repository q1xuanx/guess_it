package com.guess.it.core.utils;

import org.springframework.data.util.Pair;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateHandle {

    public static Pair<LocalDateTime, LocalDateTime> getStartAndEndDate(){
        LocalDate currentDate = LocalDate.now();
        LocalDateTime startDate = currentDate.atStartOfDay();
        LocalDateTime endDate = currentDate.atTime(23, 59, 59, 999999999);
        return Pair.of(startDate, endDate);
    }
}
