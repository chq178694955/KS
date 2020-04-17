package com.king.game.dao;

import com.king.framework.model.Criteria;
import com.king.game.entity.Vote;
import com.king.game.vo.VoteVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VoteMapper {

    int insert(Vote record);

    int update(Vote record);

    int delete(Long id);

    Vote selectOne(Long voteId);

    List<Vote> selectAll();

    List<VoteVO> find(Criteria criteria);
}