package com.king.game.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.king.framework.model.Criteria;
import com.king.game.dao.VoteRecordDetailMapper;
import com.king.game.dao.VoteRecordMapper;
import com.king.game.entity.VoteRecord;
import com.king.game.entity.VoteRecordDetail;
import com.king.game.service.IVoteRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/4/17
 * @描述
 */
@Service
@Transactional
public class VoteRecordServiceImpl implements IVoteRecordService {

    @Autowired
    private VoteRecordMapper voteRecordMapper;

    @Autowired
    private VoteRecordDetailMapper voteRecordDetailMapper;

    @Override
    public int addVoteRecord(Long userId, Long voteId, JSONArray groups) {
        VoteRecord record = new VoteRecord();
        record.setUserId(userId);
        record.setVoteId(voteId);
        record.setVoteTime(new Date());
        int result = voteRecordMapper.insert(record);
        if(!groups.isEmpty()){
            for(int i=0;i<groups.size();i++){
                JSONObject group = groups.getJSONObject(i);
                VoteRecordDetail detail = new VoteRecordDetail();
                detail.setGroupId(group.getLongValue("id"));
                detail.setItemIds(group.getString("values"));
                detail.setRecordId(record.getId());
                voteRecordDetailMapper.insert(detail);
            }
        }
        return result;
    }

    @Override
    public VoteRecord getVoteRecord(Long userId, Long voteId) {
        Criteria criteria = new Criteria();
        criteria.put("userId",userId);
        criteria.put("voteId",voteId);
        return voteRecordMapper.selectMyRecord(criteria);
    }

    @Override
    public List<VoteRecordDetail> findRecordDetails(Long recordId) {
        return voteRecordDetailMapper.selectDetails(recordId);
    }
}
