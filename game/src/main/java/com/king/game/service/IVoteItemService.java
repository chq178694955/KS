package com.king.game.service;

import com.github.pagehelper.PageInfo;
import com.king.framework.model.Criteria;
import com.king.game.entity.VoteItem;

/**
 * @创建人 chq
 * @创建时间 2020/4/14
 * @描述
 */
public interface IVoteItemService {

    int addItem(VoteItem item);

    int updateItem(VoteItem item);

    int delItem(Long id);

    PageInfo<VoteItem> find(PageInfo<VoteItem> page, Criteria criteria, Boolean isDownload);

}
