package com.king.system.dao;

import com.king.system.entity.SysApp;
import java.util.List;

public interface SysAppMapper {
    int insert(SysApp record);

    List<SysApp> selectAll();
}