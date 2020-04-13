package com.king.framework.base;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

/**
 * @创建人 chq
 * @创建时间 2020/3/18
 * @描述
 */
public interface IBaseDao<T> {

    SqlSession getSqlSessionTemplate();

    String getClassName();

    String getMapperMethod(String var1);

    int insert(T var1);

    int insert(String var1, T var2);

    int batchInsert(List<T> var1);

    int batchInsert(String var1, List<T> var2);

    int update(T var1);

    int update(String var1, T var2);

    int update(Map<String, Object> var1);

    int update(String var1, Map<String, Object> var2);

    int batchUpdate(List<T> var1);

    int batchUpdate(String var1, List<T> var2);

    <I> int delete(I var1);

    <I> int delete(String var1, I var2);

    int deleteByT(T[] var1);

    <I> int batchDelete(List<I> var1);

    <I> int batchDelete(String var1, List<I> var2);

    <I> I get(Object var1);

    <I> I get(String var1, Object var2);

    <I> List<I> find(Object var1);

    <I> List<I> find(String var1, Object var2);

    <I> List<I> findAll();

    List<T> findTByPage(PageInfo<T> var1);

    List<T> findTByT(T var1);

    int findTCountByT(T var1);

    <I> int count(I var1);

    <I> int count(String var1, I var2);

    T selectByPrimaryKey(Object var2);

}
