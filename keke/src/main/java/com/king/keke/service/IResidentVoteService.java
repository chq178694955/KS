package com.king.keke.service;

import com.king.keke.entity.ResidentVote;

import java.util.List;

public interface IResidentVoteService {

    List<ResidentVote> findByBuilding(String building,Long voteId);

    List<ResidentVote> findResidentVote(Long residentId,Long voteId);

    boolean vote(List<ResidentVote> votes);

}
