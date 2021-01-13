package com.king.keke.service;

import com.king.keke.entity.ResidentVote;

import java.util.List;

public interface IResidentVoteService {

    List<ResidentVote> findByBuilding(String building,Long voteId);

}
