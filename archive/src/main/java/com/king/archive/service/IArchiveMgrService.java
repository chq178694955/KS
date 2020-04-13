package com.king.archive.service;

import com.github.pagehelper.PageInfo;
import com.king.archive.dto.ArchiveDto;
import com.king.archive.dto.ArchiveType;
import com.king.framework.model.Criteria;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/3/19
 * @描述
 */
public interface IArchiveMgrService<T extends ArchiveDto> {

    /**
     * 获取档案类型
     * @return
     */
    ArchiveType getArchiveType();

    /**
     * 新增
     * @param userId
     * @param t
     * @return
     */
    boolean insert(Long userId, T t);

    /**
     * 删除
     * @param userId
     * @param t
     * @return
     */
    boolean delete(Long userId, T t);

    /**
     * 批量删除
     * @param userId
     * @param list
     * @return
     */
    boolean delete(Long userId, List<T> list);

    /**
     * 更新
     * @param userId
     * @param t
     * @return
     */
    boolean update(Long userId, T t);

    /**
     * 获取单个对象
     * @param id
     * @return
     */
    T get(Long id);

    /**
     * 获取对象集合
     * @param ids
     * @return
     */
    List<T> list(Long[] ids);

    /**
     * 获取集合对象
     * @param params
     * @return
     */
    List<T> list(Criteria params);

    /**
     * 获取所有数据
     * @return
     */
    List<T> listAll();

    /**
     * 分页查询
     * @param page
     * @param criteria
     * @return
     */
    PageInfo<T> findByPage(PageInfo<T> page, Criteria criteria);

    /**
     * 获取懒加载器.
     * @return
     */
    public IArchiveLazyLoader<T> getLazyLoader();

}
