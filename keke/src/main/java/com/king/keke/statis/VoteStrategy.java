package com.king.keke.statis;

import com.alibaba.fastjson.JSONObject;

/**
 * 投票策略
 */
public interface VoteStrategy {

    JSONObject vote(Long voteId);

}
