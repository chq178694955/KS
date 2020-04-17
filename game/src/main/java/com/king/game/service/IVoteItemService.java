package com.king.game.service;

import com.github.pagehelper.PageInfo;
import com.king.framework.model.Criteria;
import com.king.game.entity.VoteItem;
import com.king.game.vo.VoteItemVO;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/4/14
 * @描述
 */
public interface IVoteItemService {

    int addItem(VoteItem item);

    int updateItem(VoteItem item);

    int delItem(Long id);

    PageInfo<VoteItemVO> find(PageInfo<VoteItem> page, Criteria criteria, Boolean isDownload);

    List<VoteItemVO> getItemByGroupIds(List<Long> groupIds);

}
