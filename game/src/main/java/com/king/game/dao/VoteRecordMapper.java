package com.king.game.dao;

import com.king.framework.model.Criteria;
import com.king.game.entity.VoteRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VoteRecordMapper {
    int insert(VoteRecord record);

    List<VoteRecord> selectAll();

    VoteRecord selectMyRecord(Criteria criteria);
}