package com.king.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.king.framework.model.Criteria;
import com.king.system.dao.SysConfigMapper;
import com.king.system.entity.SysConfig;
import com.king.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/4/22
 * @描述
 */
@Service
@Transactional
public class SysConfigServiceImpl implements ISysConfigService {

    @Autowired
    private SysConfigMapper sysConfigMapper;

    @Override
    public int addConfig(SysConfig conf) {
        return sysConfigMapper.insert(conf);
    }

    @Override
    public int updateConfig(SysConfig conf) {
        return sysConfigMapper.update(conf);
    }

    @Override
    public int delConfig(String text) {
        return sysConfigMapper.delete(text);
    }

    @Override
    public int deleteByProjName(String projectName) {
        return sysConfigMapper.deleteByProjName(projectName);
    }

    @Override
    public List<SysConfig> findAll() {
        return sysConfigMapper.selectAll();
    }

    @Override
    public PageInfo<SysConfig> find(PageInfo<SysConfig> page, Criteria criteria, Boolean isDownload) {
        if(!isDownload){
            PageHelper.startPage(page.getPageNum(),page.getPageSize());
        }
        List<SysConfig> confs = sysConfigMapper.find(criteria);
        PageInfo<SysConfig> pageInfo = new PageInfo<SysConfig>(confs);
        pageInfo.setPageNum(page.getPageNum());
        pageInfo.setPageSize(page.getPageSize());
        return pageInfo;
    }

    @Override
    public SysConfig findByKey(String text) {
        return sysConfigMapper.findByKey(text);
    }
}
