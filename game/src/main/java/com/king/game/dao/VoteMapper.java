package com.king.game.dao;

import com.king.game.entity.Vote;
import java.util.List;

public interface VoteMapper {
    int insert(Vote record);

    List<Vote> selectAll();
}