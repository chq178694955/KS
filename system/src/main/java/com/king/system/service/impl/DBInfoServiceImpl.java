package com.king.system.service.impl;

import com.king.framework.ds.DataSourceSign;
import com.king.framework.ds.DataSourceType;
import com.king.system.dao.DBInfoMapper;
import com.king.system.entity.DBInfo;
import com.king.system.service.IDBInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/10/21
 * @描述
 */
@Service
public class DBInfoServiceImpl implements IDBInfoService {

    @Autowired
    private DBInfoMapper dbInfoMapper;

    @DataSourceSign(value = DataSourceType.SHARE)
    @Override
    public List<DBInfo> list() {
        List<DBInfo> list =  dbInfoMapper.selectAll();
        return list;
    }
}
