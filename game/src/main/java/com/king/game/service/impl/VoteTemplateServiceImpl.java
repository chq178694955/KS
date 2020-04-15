package com.king.game.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.king.framework.model.Criteria;
import com.king.game.dao.VoteTemplateMapper;
import com.king.game.entity.VoteTemplate;
import com.king.game.service.IVoteTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/4/14
 * @描述
 */
@Service
public class VoteTemplateServiceImpl implements IVoteTemplateService {

    @Autowired
    private VoteTemplateMapper voteTemplateMapper;

    @Override
    public int addVoteTemplate(VoteTemplate template) {
        return voteTemplateMapper.insert(template);
    }

    @Override
    public int updateVoteTemplate(VoteTemplate template) {
        return voteTemplateMapper.update(template);
    }

    @Override
    public int deleteVoteTemplate(Long id) {
        return voteTemplateMapper.delete(id);
    }

    @Override
    public PageInfo<VoteTemplate> find(PageInfo<VoteTemplate> page, Criteria criteria, Boolean isDownload) {
        if(!isDownload){
            PageHelper.startPage(page.getPageNum(),page.getPageSize());
        }
        List<VoteTemplate> list = voteTemplateMapper.find(criteria);
        PageInfo<VoteTemplate> pageInfo = new PageInfo<>(list);
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setPageSize(page.getPageSize());
        return pageInfo;
    }

}
