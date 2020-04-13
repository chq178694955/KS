package com.king.framework.base;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/3/18
 * @描述
 */
public interface IBaseService<T> {

    List<T> selectAll();

    T selectByPrimaryKey(Object var1);

    int insert(T var1);

    int updateByPrimaryKey(T var1);

    int deleteByPrimaryKey(Object[] var1);

    PageInfo<T> findTByPage(PageInfo<T> var1, T var2);

    List<T> findTByT(T var1);

    T findTByTOne(T var1);

    List<T> find(Object var1);

}
