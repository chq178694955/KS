package com.king.game.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.king.framework.model.Criteria;
import com.king.framework.utils.DateUtils;
import com.king.game.conts.VoteEnum;
import com.king.game.dao.VoteItemGroupMapper;
import com.king.game.dao.VoteItemMapper;
import com.king.game.dao.VoteMapper;
import com.king.game.dao.VoteTemplateMapper;
import com.king.game.entity.Vote;
import com.king.game.entity.VoteItem;
import com.king.game.entity.VoteItemGroup;
import com.king.game.entity.VoteTemplate;
import com.king.game.service.IVoteService;
import com.king.game.vo.VoteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/4/15
 * @描述
 */
@Transactional
@Service
public class VoteServiceImpl implements IVoteService {

    @Autowired
    private VoteMapper voteMapper;

    @Autowired
    private VoteItemGroupMapper voteItemGroupMapper;

    @Autowired
    private VoteTemplateMapper voteTemplateMapper;

    @Autowired
    private VoteItemMapper voteItemMapper;

    @Override
    public int addVote(Vote vote) {
        return voteMapper.insert(vote);
    }

    @Override
    public int addVote(Vote vote, JSONArray groups) {
        if(!groups.isEmpty()){
            Long templateId = 0L;
            for(int i=0;i<groups.size();i++){
                JSONObject group = groups.getJSONObject(i);
                VoteItemGroup voteGroup = JSONObject.parseObject(group.toString(), VoteItemGroup.class);

                if(i == 0){
                    //只有第一次进来创建默认模板
                    VoteTemplate template = new VoteTemplate();
                    template.setName(voteGroup.getName());
                    voteTemplateMapper.insert(template);
                    templateId = template.getId();
                    vote.setTemplateId(templateId);
                }

                voteItemGroupMapper.insert(voteGroup);

                Criteria criteria = new Criteria();
                criteria.put("templateId",templateId);
                criteria.put("groupId",voteGroup.getId());
                voteTemplateMapper.insertTemplateGroupRel(criteria);

                if(voteGroup.getId() > 0){
                    JSONArray items = group.getJSONArray("items");
                    for(int j=0;j<items.size();j++){
                        String itemName = items.getString(j);
                        VoteItem voteItem = new VoteItem();
                        voteItem.setName(itemName);
                        voteItem.setValue(String.valueOf(j));//用下标作为value
                        voteItem.setGroupId(voteGroup.getId());
                        voteItemMapper.insert(voteItem);
                    }
                }
            }
        }
        int result = voteMapper.insert(vote);
        return result;
    }

    @Override
    public int updateVote(Vote vote) {
        return voteMapper.update(vote);
    }

    @Override
    public int delVote(Long id) {
        Vote vote = voteMapper.selectOne(id);

        int result = voteMapper.delete(id);

        if(vote.getTemplateId() > 0){
            VoteTemplate template = voteTemplateMapper.selectOne(vote.getTemplateId());
            List<VoteItemGroup> groups = voteItemGroupMapper.selectByTemplateId(vote.getTemplateId());

            voteTemplateMapper.deleteTemplateGroupRel(vote.getTemplateId());

            if(null != groups && groups.size() > 0){
                for(VoteItemGroup group : groups){
                    voteItemMapper.deleteByGroupId(group.getId());
                    voteItemGroupMapper.delete(group.getId());
                }
            }
            voteTemplateMapper.delete(template.getId());
        }
        return result;
    }

    @Override
    public PageInfo<VoteVO> find(PageInfo<Vote> page, Criteria criteria, Boolean isDownload) {
        if(!isDownload){
            PageHelper.startPage(page.getPageNum(),page.getPageSize());
        }
        List<VoteVO> list = voteMapper.find(criteria);
        if(list != null && list.size() > 0){
            for(VoteVO vo : list){
                vo.setStartTimeDesc(DateUtils.format(vo.getStartTime(),"yyyy-MM-dd"));
                vo.setEndTimeDesc(DateUtils.format(vo.getEndTime(),"yyyy-MM-dd"));
                vo.setCreateTimeDesc(DateUtils.format(vo.getCreateTime(),"yyyy-MM-dd"));
                vo.setStatusDesc(VoteEnum.getDesc(vo.getStatus()));
            }
        }
        PageInfo<VoteVO> pageInfo = new PageInfo<>(list);
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setPageSize(page.getPageSize());
        return pageInfo;
    }

    @Override
    public Vote getVote(Long voteId) {
        return voteMapper.selectOne(voteId);
    }
}
