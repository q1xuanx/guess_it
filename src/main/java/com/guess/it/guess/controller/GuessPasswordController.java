package com.guess.it.guess.controller;


import com.guess.it.core.dto.ApiResponse;
import com.guess.it.guess.dto.GuessPassRequest;
import com.guess.it.guess.service.ExactPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class GuessPasswordController {

    private final ExactPasswordService guessPasswordService;

    @PostMapping("/guess")
    public ResponseEntity<ApiResponse<Boolean>> guessPassword(@RequestBody GuessPassRequest guess) {
        ApiResponse<Boolean> apiResponse = guessPasswordService.guessPassword(guess.getPassword());
        if (apiResponse.getCode() == 400) {
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
