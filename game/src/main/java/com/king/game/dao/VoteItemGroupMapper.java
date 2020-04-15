package com.king.game.dao;

import com.king.framework.model.Criteria;
import com.king.game.entity.VoteItemGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VoteItemGroupMapper {

    int insert(VoteItemGroup record);

    int update(VoteItemGroup record);

    int delete(Long id);

    List<VoteItemGroup> selectAll();

    List<VoteItemGroup> find(Criteria criteria);
}