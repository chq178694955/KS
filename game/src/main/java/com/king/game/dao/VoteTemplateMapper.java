package com.king.game.dao;

import com.king.framework.model.Criteria;
import com.king.game.entity.VoteTemplate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VoteTemplateMapper {

    int insert(VoteTemplate record);

    int update(VoteTemplate record);

    int delete(Long id);

    List<VoteTemplate> selectAll();

    List<VoteTemplate> find(Criteria criteria);
}