package com.king.keke.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.king.framework.model.Criteria;
import com.king.framework.utils.DateUtils;
import com.king.keke.dao.VoteInfoDao;
import com.king.keke.dao.VoteOptionDao;
import com.king.keke.dao.VoteOptionItemDao;
import com.king.keke.entity.VoteInfo;
import com.king.keke.entity.VoteOption;
import com.king.keke.entity.VoteOptionItem;
import com.king.keke.service.IVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VoteServiceImpl implements IVoteService {

    @Autowired
    private VoteInfoDao voteInfoDao;
    @Autowired
    private VoteOptionDao voteOptionDao;
    @Autowired
    private VoteOptionItemDao voteOptionItemDao;

    @Override
    public PageInfo<VoteInfo> findInfo(PageInfo<VoteInfo> page, Criteria criteria, boolean isDownload) {
        if(!isDownload){
            PageHelper.startPage(page.getPageNum(),page.getPageSize());
        }
        List<VoteInfo> list = voteInfoDao.find(criteria);
        list.forEach(i->i.setCreateTimeDesc(DateUtils.format(i.getCreateTime(),DateUtils.YMDHMS)));
        PageInfo<VoteInfo> resultPage = new PageInfo<>(list);
        resultPage.setPageNum(page.getPageNum());
        resultPage.setPageSize(page.getPageSize());
        return resultPage;
    }

    @Override
    public List<VoteOption> findOption(Long voteId) {
        Criteria criteria = new Criteria();
        criteria.put("voteId",voteId);
        List<VoteOption> options = voteOptionDao.find("find",criteria);
//        final List<Long> voteOptionIds = new ArrayList<>();
//        options.forEach(o->voteOptionIds.add(o.getId()));
//        criteria.put("voteOptionIds",voteOptionIds);
//        List<VoteOptionItem> items = voteOptionItemDao.find("find",criteria);
//        options.forEach((o)->{
//            List<VoteOptionItem> owners = items.stream().filter(i->i.getVoteOptionId() == o.getId()).collect(Collectors.toList());
//            o.setItems(owners);
//        });

        return options;
    }

    @Override
    public List<VoteOptionItem> findOptionItem(Long voteOptionId) {
        Criteria criteria = new Criteria();
        criteria.put("voteOptionId",voteOptionId);
        List<VoteOptionItem> items = voteOptionItemDao.find("find",criteria);
        return items;
    }
}
