package com.king.game.service;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.king.framework.model.Criteria;
import com.king.game.entity.Vote;
import com.king.game.vo.VoteVO;

/**
 * @创建人 chq
 * @创建时间 2020/4/14
 * @描述
 */
public interface IVoteService {

    int addVote(Vote vote);

    int addVote(Vote vote,JSONArray groups);

    int updateVote(Vote vote);

    int delVote(Long id);

    PageInfo<VoteVO> find(PageInfo<Vote> page, Criteria criteria, Boolean isDownload);

    Vote getVote(Long voteId);

}
