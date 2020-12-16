package com.king.keke.service;

import com.github.pagehelper.PageInfo;
import com.king.framework.model.Criteria;
import com.king.keke.entity.ResidentInfo;

import java.util.List;

public interface IResidentInfoService {

    PageInfo<ResidentInfo> findPage(PageInfo<ResidentInfo> page, Criteria criteria, boolean isDownload);

    List<ResidentInfo> findAll();

    boolean batchAdd(List<ResidentInfo> infos);

    boolean batchModify(List<ResidentInfo> infos);

    boolean batchDelete(List<Long> ids);

    boolean add(ResidentInfo info);

    boolean modify(ResidentInfo info);

    boolean del(Long id);

}
