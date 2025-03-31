package com.guess.it.leaderboard.controller;

import com.guess.it.core.dto.ApiResponse;
import com.guess.it.leaderboard.dto.NameUserRequest;
import com.guess.it.leaderboard.model.LeaderBoard;
import com.guess.it.leaderboard.service.LeaderBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class LeaderBoardController {
    private final LeaderBoardService leaderBoardService;
    private final SimpMessagingTemplate messagingTemplate;
    @MessageMapping("/correct-user")
    public void addCorrectUser(@Payload NameUserRequest nameUserRequest) {
        ApiResponse<Boolean> saveUser = leaderBoardService.save(nameUserRequest.getNameUser());
        if (saveUser.getData()){
            messagingTemplate.convertAndSend("/topic/leader-board", "update");
        }
    }

    @GetMapping("/leaderboards")
    public ResponseEntity<ApiResponse<Map<String,Object>>> getListOfLeaderBoard(@RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "5") int pageSize) {
        ApiResponse<Map<String, Object>> getList = leaderBoardService.getListOfUserInLeaderBoard(currentPage, pageSize);
        return new ResponseEntity<>(getList, HttpStatus.OK);
    }
}
