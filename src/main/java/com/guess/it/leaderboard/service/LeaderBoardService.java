package com.guess.it.leaderboard.service;

import com.guess.it.core.dto.ApiResponse;
import com.guess.it.core.utils.PageHandle;
import com.guess.it.leaderboard.model.LeaderBoard;
import com.guess.it.leaderboard.repository.LeaderBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LeaderBoardService {
    private final LeaderBoardRepository leaderBoardRepository;

    public ApiResponse<Boolean> save(String nameUser) {
         if (nameUser == null || nameUser.isBlank()){
            return new ApiResponse<>(400, "Name user guess correct not found", false);
        }
        LeaderBoard leaderBoard = LeaderBoard.builder()
                .userGuessCorrect(nameUser)
                .dateTime(LocalDateTime.now())
                .build();
        leaderBoardRepository.save(leaderBoard);
        return new ApiResponse<>(200, "Success", true);
    }

    public ApiResponse<Map<String, Object>> getListOfUserInLeaderBoard(int currentPage, int pageSize) {
        List<LeaderBoard> getList = leaderBoardRepository.getLeaderBoardByLimitAndOffset(pageSize, (currentPage - 1) * pageSize);
        int totalItems = leaderBoardRepository.countLeaderBoardBy();
        if (getList.isEmpty()){
            return new ApiResponse<>(204, "Content not found", null);
        }
        Map<String, Object> data = PageHandle.page(totalItems, pageSize, getList);
        return new ApiResponse<>(200, "Get list success", data);
    }
}
