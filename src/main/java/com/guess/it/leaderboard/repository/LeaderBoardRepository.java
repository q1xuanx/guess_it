package com.guess.it.leaderboard.repository;

import com.guess.it.leaderboard.model.LeaderBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LeaderBoardRepository extends JpaRepository<LeaderBoard, Integer> {
    @Query("SELECT e FROM LeaderBoard as e ORDER BY e.dateTime DESC LIMIT :limit OFFSET :offset")
    List<LeaderBoard> getLeaderBoardByLimitAndOffset(@Param("limit") int limit, @Param("offset") int offset);
    @Query("SELECT COUNT(*) FROM LeaderBoard")
    int countLeaderBoardBy();
}
