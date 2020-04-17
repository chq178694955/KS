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

    VoteTemplate selectOne(Long templateId);

    List<VoteTemplate> find(Criteria criteria);

    List<VoteTemplate> findTemplateByGroupId(Long groupId);

    int insertTemplateGroupRel(Criteria criteria);

    int deleteTemplateGroupRel(Long templateId);
}