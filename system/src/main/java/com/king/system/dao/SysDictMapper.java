package com.king.system.dao;

import com.king.framework.model.Criteria;
import com.king.system.entity.SysDict;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysDictMapper {

    int insert(SysDict record);

    int update(SysDict record);

    int delete(Long dictSn);

    List<SysDict> selectAll();

    List<SysDict> selectTopDicts();

    List<SysDict> select(Criteria criteria);

    SysDict selectOne(Long dictSn);
}