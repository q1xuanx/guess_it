package com.guess.it.guess.controller;

import com.guess.it.core.dto.ApiResponse;
import com.guess.it.guess.model.WrongGuess;
import com.guess.it.guess.service.WrongGuessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WrongPassController {
    private final WrongGuessService wrongGuessService;
    @GetMapping("/wrong-guesses")
    public ResponseEntity<ApiResponse<List<WrongGuess>>> getListOfWrongGuess() {
        return new ResponseEntity<>(wrongGuessService.getListOfWrongGuess(), HttpStatus.OK);
    }
}
