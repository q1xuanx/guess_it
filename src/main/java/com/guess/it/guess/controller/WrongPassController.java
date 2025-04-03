package com.guess.it.guess.controller;

import com.guess.it.core.dto.ApiResponse;
import com.guess.it.guess.service.WrongGuessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class WrongPassController {
    private final WrongGuessService wrongGuessService;
    @GetMapping("/wrong-guesses")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getListOfWrongGuess(@RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "5") int limit ) {
        return new ResponseEntity<>(wrongGuessService.getListOfWrongGuess(currentPage, limit), HttpStatus.OK);
    }
}
