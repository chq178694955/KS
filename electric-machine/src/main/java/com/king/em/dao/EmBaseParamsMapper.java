package com.king.em.dao;

import com.king.em.entity.EmBaseParams;
import java.util.List;

import com.king.framework.model.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EmBaseParamsMapper {

    int delete(Integer id);

    int insert(EmBaseParams record);

    int update(@Param("record") EmBaseParams record);

    List<EmBaseParams> find(Criteria criteria);

    EmBaseParams selectOne(Integer id);

}