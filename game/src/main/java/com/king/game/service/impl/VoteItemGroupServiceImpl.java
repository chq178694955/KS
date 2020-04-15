package com.king.game.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.king.framework.model.Criteria;
import com.king.game.dao.VoteItemGroupMapper;
import com.king.game.entity.VoteItemGroup;
import com.king.game.service.IVoteItemGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/4/14
 * @描述
 */
@Service
public class VoteItemGroupServiceImpl implements IVoteItemGroupService {

    @Autowired
    private VoteItemGroupMapper voteItemGroupMapper;

    @Override
    public int addGroup(VoteItemGroup group) {
        return voteItemGroupMapper.insert(group);
    }

    @Override
    public int updateGroup(VoteItemGroup group) {
        return voteItemGroupMapper.update(group);
    }

    @Override
    public int delGroup(Long id) {
        return voteItemGroupMapper.delete(id);
    }

    @Override
    public PageInfo<VoteItemGroup> findPage(PageInfo<VoteItemGroup> page, Criteria criteria, Boolean isDownload) {
        if(!isDownload){
            PageHelper.startPage(page.getPageNum(),page.getPageSize());
        }
        List<VoteItemGroup> groups = voteItemGroupMapper.find(criteria);
        PageInfo<VoteItemGroup> pageInfo = new PageInfo<VoteItemGroup>(groups);
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setPageSize(page.getPageSize());
        return pageInfo;
    }

    @Override
    public List<VoteItemGroup> selectAll() {
        return voteItemGroupMapper.selectAll();
    }
}
