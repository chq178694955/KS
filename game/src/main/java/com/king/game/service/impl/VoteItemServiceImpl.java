package com.king.game.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.king.framework.model.Criteria;
import com.king.game.dao.VoteItemMapper;
import com.king.game.entity.VoteItem;
import com.king.game.service.IVoteItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/4/14
 * @描述
 */
@Service
public class VoteItemServiceImpl implements IVoteItemService {

    @Autowired
    private VoteItemMapper voteItemMapper;

    @Override
    public int addItem(VoteItem item) {
        return voteItemMapper.insert(item);
    }

    @Override
    public int updateItem(VoteItem item) {
        return voteItemMapper.update(item);
    }

    @Override
    public int delItem(Long id) {
        return voteItemMapper.delete(id);
    }

    @Override
    public PageInfo<VoteItem> find(PageInfo<VoteItem> page, Criteria criteria, Boolean isDownload) {
        if(!isDownload){
            PageHelper.startPage(page.getPageNum(),page.getPageSize());
        }
        List<VoteItem> items = voteItemMapper.find(criteria);
        PageInfo<VoteItem> pageInfo = new PageInfo<VoteItem>(items);
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setPageSize(page.getPageSize());
        return pageInfo;
    }
}
