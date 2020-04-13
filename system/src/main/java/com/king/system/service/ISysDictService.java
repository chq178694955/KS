package com.king.system.service;

import com.github.pagehelper.PageInfo;
import com.king.framework.model.Criteria;
import com.king.system.entity.SysDict;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/4/9
 * @描述
 */
public interface ISysDictService {

    List<SysDict> findAll();

    List<SysDict> findTopDicts();

    PageInfo<SysDict> find(PageInfo<SysDict> page,Criteria criteria,Boolean isDownload);

    boolean isExistsDict(Long dictSn);

    int addDict(SysDict dict);

    int updateDict(SysDict dict);

    int deleteDict(Long dictSn);

}
