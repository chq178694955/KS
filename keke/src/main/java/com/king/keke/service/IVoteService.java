package com.king.keke.service;

import com.github.pagehelper.PageInfo;
import com.king.framework.model.Criteria;
import com.king.keke.entity.VoteInfo;
import com.king.keke.entity.VoteOption;
import com.king.keke.entity.VoteOptionItem;

import java.util.List;

public interface IVoteService {

    PageInfo<VoteInfo> findInfo(PageInfo<VoteInfo> page, Criteria criteria, boolean isDownload);

    List<VoteOption> findOption(Long voteId);

    List<VoteOptionItem> findOptionItem(Long voteOptionId);

}
