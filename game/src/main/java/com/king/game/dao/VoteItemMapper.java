package com.king.game.dao;

import com.king.framework.model.Criteria;
import com.king.game.entity.VoteItem;
import com.king.game.vo.VoteItemVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VoteItemMapper {

    int insert(VoteItem record);

    int update(VoteItem record);

    int delete(Long id);

    int deleteByGroupId(Long groupId);

    List<VoteItem> selectAll();

    List<VoteItemVO> find(Criteria criteria);

    List<VoteItemVO> selectItemByGroupIds(List<Long> groupIds);
}