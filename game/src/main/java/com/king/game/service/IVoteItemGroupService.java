package com.king.game.service;

import com.github.pagehelper.PageInfo;
import com.king.framework.model.Criteria;
import com.king.game.entity.VoteItemGroup;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/4/14
 * @描述
 */
public interface IVoteItemGroupService {

    int addGroup(VoteItemGroup group);

    int updateGroup(VoteItemGroup group);

    int delGroup(Long id);

    PageInfo<VoteItemGroup> findPage(PageInfo<VoteItemGroup> page, Criteria criteria, Boolean isDownload);

    List<VoteItemGroup> selectAll();

}
