package com.king.system.dao;

import com.king.framework.model.Criteria;
import com.king.system.entity.SysConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysConfigMapper {

    int insert(SysConfig record);

    int update(SysConfig record);

    int delete(String text);

    int deleteByProjName(String projName);

    List<SysConfig> selectAll();

    List<SysConfig> find(Criteria criteria);

    SysConfig findByKey(@Param("text") String text);
}