package com.king.game.dao;

import com.king.framework.model.Criteria;
import com.king.game.entity.VoteRecordDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VoteRecordDetailMapper {
    int insert(VoteRecordDetail record);

    List<VoteRecordDetail> selectAll();

    List<VoteRecordDetail> selectDetails(Long recordId);
}