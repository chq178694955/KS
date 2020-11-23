package com.king.framework.base;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @创建人 chq
 * @创建时间 2020/3/18
 * @描述
 */
public abstract class BaseDaoImpl<T> implements IBaseDao<T> {

    protected Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

    protected static final String GET = "get";
    protected static final String FIND = "find";
    protected static final String PAGE = "page";
    protected static final String INSERT = "insert";
    protected static final String BATCH_INSERT = "batchInsert";
    protected static final String UPDATE = "update";
    protected static final String BATCH_UPDATE = "batchUpdate";
    protected static final String DELETE = "delete";
    protected static final String BATCH_DELETE = "batchDelete";
    protected static final String FIND_ALL = "findAll";
    protected static final String FIND_T_BY_PAGE = "findTByPage";
    protected static final String COUNT = "count";

    protected String className = this.getClass().getName();

    public String getClassName() {
        return this.className;
    }

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public SqlSession getSqlSessionTemplate() {
//        return sqlSessionFactory.openSession();
        return sqlSessionTemplate;
    }

    public String getMapperMethod(String methodName) {
        StringBuilder sb = (new StringBuilder(this.className)).append(".").append(methodName);
        return sb.toString();
    }

    public int insert(T entity) {
        return this.insert(INSERT, entity);
    }

    public int insert(String mapperMethod, T entity) {
        return this.getSqlSessionTemplate().insert(this.getMapperMethod(mapperMethod), entity);
    }

    public int batchInsert(List<T> entityList) {
        return this.batchInsert("batchInsert", entityList);
    }

    public int batchInsert(String mapperMethod, List<T> entityList) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH,false);
        int count = 0;
        for(int i=0;i<entityList.size();i++){
            this.insert(entityList.get(i));
            count ++;
        }
        sqlSession.commit();
        return count;
    }

    public int update(T entity) {
        return this.update("update", entity);
    }

    public int update(String mapperMethod, T entity) {
        return this.getSqlSessionTemplate().update(this.getMapperMethod(mapperMethod), entity);
    }

    public int update(Map<String, Object> objectMap) {
        return this.update("update", objectMap);
    }

    public int update(String mapperMethod, Map<String, Object> objectMap) {
        return this.getSqlSessionTemplate().update(this.getMapperMethod(mapperMethod), objectMap);
    }

    public int batchUpdate(List<T> entityList) {
        return this.batchUpdate("batchUpdate", entityList);
    }

    public int batchUpdate(String mapperMethod, List<T> entityList) {
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH,false);
        int count = 0;
        for(int i=0;i<entityList.size();i++){
            this.update(entityList.get(i));
            count ++;
        }
        sqlSession.commit();
        return count;
    }

    public <I> int delete(I id) {
        return this.delete("delete", id);
    }

    public <I> int delete(String mapperMethod, I params) {
        return this.getSqlSessionTemplate().delete(this.getMapperMethod(mapperMethod), params);
    }

    public int deleteByT(T[] entityArray) {
        return this.delete("deleteByT", entityArray);
    }

    public <I> int batchDelete(List<I> ids) {
        return this.delete("batchDelete", ids);
    }

    public <I> int batchDelete(String mapperMethod, List<I> ids) {
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH,false);
        int count = 0;
        for(int i=0;i<ids.size();i++){
            this.delete(ids.get(i));
            count ++;
        }
        sqlSession.commit();
        return count;
    }

    public <I> I get(Object obj) {
        return this.get("get", obj);
    }

    public <I> I get(String mapperMethod, Object obj) {
        return this.getSqlSessionTemplate().selectOne(this.getMapperMethod(mapperMethod), obj);
    }

    public <I> List<I> find(Object params) {
        return this.find("find", params);
    }

    public <I> List<I> find(String mapperMethod, Object params) {
        return this.getSqlSessionTemplate().selectList(this.getMapperMethod(mapperMethod), params);
    }

    public <I> List<I> findAll() {
        return this.find(FIND_ALL, (Object)null);
    }

    public List<T> findTByPage(PageInfo<T> page) {
        return this.find(FIND_T_BY_PAGE, page);
    }

    public List<T> findTByT(T t) {
        return this.find("findTByT", t);
    }

    public int findTCountByT(T t) {
        return (Integer)this.getSqlSessionTemplate().selectOne(this.getMapperMethod("findTCountByT"), t);
    }

    public <I> int count(I params) {
        return this.count(COUNT, params);
    }

    public <I> int count(String mapperMethod, I params) {
        return (Integer)this.getSqlSessionTemplate().selectOne(this.getMapperMethod(mapperMethod), params);
    }

    @Override
    public T selectByPrimaryKey(Object var1) {
        return this.getSqlSessionTemplate().selectOne(this.getMapperMethod("selectByPrimaryKey"), var1);
    }
}
