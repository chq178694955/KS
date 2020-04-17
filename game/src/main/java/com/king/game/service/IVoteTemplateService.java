package com.king.game.service;

import com.github.pagehelper.PageInfo;
import com.king.framework.model.Criteria;
import com.king.game.entity.VoteTemplate;

/**
 * @创建人 chq
 * @创建时间 2020/4/14
 * @描述
 */
public interface IVoteTemplateService {

    int addVoteTemplate(VoteTemplate template);

    int updateVoteTemplate(VoteTemplate template);

    int deleteVoteTemplate(Long id);

    PageInfo<VoteTemplate> find(PageInfo<VoteTemplate> page, Criteria criteria,Boolean isDownload);

    VoteTemplate getTemplate(Long templateId);

}
