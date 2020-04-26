package com.king.system.service;

import com.github.pagehelper.PageInfo;
import com.king.framework.model.Criteria;
import com.king.system.entity.SysConfig;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/4/22
 * @描述
 */
public interface ISysConfigService {

    int addConfig(SysConfig conf);

    int updateConfig(SysConfig conf);

    int delConfig(String text);

    int deleteByProjName(String projectName);

    List<SysConfig> findAll();

    PageInfo<SysConfig> find(PageInfo<SysConfig> page, Criteria criteria, Boolean isDownload);

    SysConfig findByKey(String text);

}
