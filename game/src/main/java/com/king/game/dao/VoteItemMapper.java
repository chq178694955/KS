package com.king.game.dao;

import com.king.framework.model.Criteria;
import com.king.game.entity.VoteItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VoteItemMapper {

    int insert(VoteItem record);

    int update(VoteItem record);

    int delete(Long id);

    List<VoteItem> selectAll();

    List<VoteItem> find(Criteria criteria);
}