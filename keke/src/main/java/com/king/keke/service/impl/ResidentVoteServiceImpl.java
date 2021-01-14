package com.king.keke.service.impl;

import com.king.framework.model.Criteria;
import com.king.keke.dao.ResidentVoteDao;
import com.king.keke.entity.ResidentVote;
import com.king.keke.service.IResidentVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("residentVoteService")
public class ResidentVoteServiceImpl implements IResidentVoteService {

    @Autowired
    private ResidentVoteDao residentVoteDao;

    @Override
    public List<ResidentVote> findByBuilding(String building,Long voteId) {
        Criteria criteria = new Criteria();
        criteria.put("building",building);
        criteria.put("voteId",voteId);
        List<ResidentVote> list = residentVoteDao.find("find",criteria);
        for(ResidentVote rv : list){
            if(rv.getVoteStatus() == 0){
                rv.setVoteStatusDesc("未投");
            }else{
                rv.setVoteStatusDesc("已投");
            }
        }
        return list;
    }

    @Override
    public List<ResidentVote> findResidentVote(Long residentId, Long voteId) {
        Criteria criteria = new Criteria();
        criteria.put("residentId",residentId);
        criteria.put("voteId",voteId);
        List<ResidentVote> list = residentVoteDao.find("findResidentVote",criteria);
        return list;
    }

    @Override
    public boolean vote(List<ResidentVote> votes) {
        for(ResidentVote vote : votes){
            residentVoteDao.delete(vote);
            residentVoteDao.insert(vote);
        }
        return true;
    }
}
