package com.king.framework.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/3/18
 * @描述
 */
//@Service
public abstract class BaseServiceImpl<T> implements IBaseService<T>{

    protected Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    @Autowired
    protected IBaseDao<T> baseDao;

    public List<T> selectAll() {
        return this.baseDao.findAll();
    }

    public T selectByPrimaryKey(Object id) {
        return this.baseDao.get(id);
    }

    public int insert(T t) {
        return this.baseDao.insert(t);
    }

    public int updateByPrimaryKey(T t) {
        return this.baseDao.update(t);
    }

    public int deleteByPrimaryKey(Object[] id) {
        return this.baseDao.delete(id);
    }

    public PageInfo<T> findTByPage(PageInfo<T> page, T t) {
        if (page.getPageSize() != -1) {
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
        }

        List<T> list = this.baseDao.findTByPage(page);
        PageInfo<T> pageInfo = new PageInfo(list);
        page.setList(list);
        return page;
    }

    public List<T> findTByT(T t) {
        return this.baseDao.findTByT(t);
    }

    public T findTByTOne(T t) {
        List<T> tOne = this.findTByT(t);
        return tOne.size() > 0 ? tOne.get(0) : null;
    }

    public List<T> find(Object params) {
        return this.baseDao.find(params);
    }
}
