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

@Service("voteService")
public class VoteServiceImpl implements IVoteService {

    @Autowired
    private VoteInfoDao voteInfoDao;
    @Autowired
    private VoteOptionDao voteOptionDao;
    @Autowired
    private VoteOptionItemDao voteOptionItemDao;

    @Override
    public VoteInfo findVoteById(Long voteId) {
        return voteInfoDao.get("findById",voteId);
    }

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
        options.forEach(o->o.setTypeDesc(o.getType().intValue() == 0 ? "单选" : "多选"));
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

    @Override
    public boolean addVote(VoteInfo vote) {
        return voteInfoDao.insert(vote) > 0 ? true : false;
    }

    @Override
    public VoteInfo getByTitle(String title) {
        Criteria criteria = new Criteria();
        criteria.put("title",title);
        return voteInfoDao.get(criteria);
    }

    @Override
    public boolean delVote(Long id) {
        return voteInfoDao.delete(id) > 0 ? true : false;
    }

    @Override
    public VoteOption getOption(Long voteId, String name) {
        Criteria criteria = new Criteria();
        criteria.put("voteId",voteId);
        criteria.put("name",name);
        return voteOptionDao.get(criteria);
    }

    @Override
    public boolean addVoteOption(VoteOption option) {
        return voteOptionDao.insert(option) > 0 ? true : false;
    }

    @Override
    public VoteOptionItem getOptionItem(Criteria criteria) {
        return voteOptionItemDao.get(criteria);
    }

    @Override
    public boolean addVoteOptionItem(VoteOptionItem item) {
        return voteOptionItemDao.insert(item) > 0 ? true : false;
    }

    @Override
    public boolean delVoteOption(Long id) {
        return voteOptionDao.delete(id) > 0 ? true : false;
    }

    @Override
    public boolean delVoteOptionItem(Long id) {
        return voteOptionItemDao.delete(id) > 0 ? true :false;
    }
}
