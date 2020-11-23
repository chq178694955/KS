package com.king.system.dao;

import com.king.framework.ds.DataSourceSign;
import com.king.framework.ds.DataSourceType;
import com.king.system.entity.DBInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DBInfoMapper {
    int insert(DBInfo record);

    List<DBInfo> selectAll();
}