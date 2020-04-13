package com.king.archive.service;

import com.king.archive.dmo.ArchiveDmo;
import com.king.archive.dto.ArchiveDto;

/**
 * 档案懒加载接口.
 */
public interface IArchiveLazyLoader<T extends ArchiveDto> {

    /**
     * 加载Dto属性.
     * <p>
     * 包含数据字典描述、关联档案名称等属性
     * </p>
     * @param dto
     */
    public void load(T dto);
    
    /**
     * 获取数据缓存内存对象.
     * <p>
     *  转换Dto对象为数据缓存内存对象
     * </p>
     * @param dto
     * @return
     */
    public ArchiveDmo getDmo(T dto);
}
