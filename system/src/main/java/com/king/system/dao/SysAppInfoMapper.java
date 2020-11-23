package com.king.system.dao;

import com.king.system.entity.SysAppInfo;
import java.util.List;

public interface SysAppInfoMapper {
    int insert(SysAppInfo record);

    List<SysAppInfo> selectAll();
}