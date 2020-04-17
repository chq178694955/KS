package com.king.game.service;

import com.alibaba.fastjson.JSONArray;
import com.king.game.entity.VoteRecord;
import com.king.game.entity.VoteRecordDetail;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/4/17
 * @描述
 */
public interface IVoteRecordService {

    int addVoteRecord(Long userId, Long voteId, JSONArray groups);

    VoteRecord getVoteRecord(Long userId, Long voteId);

    List<VoteRecordDetail> findRecordDetails(Long recordId);

}
