package com.king.em.dao;

import com.king.em.entity.EmAttrType;
import java.util.List;

import com.king.framework.model.Criteria;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmAttrTypeMapper {

    EmAttrType findByName(String text);

    int insert(EmAttrType record);

    int update(EmAttrType record);

    int delete(Long id);

    List<EmAttrType> find(Criteria criteria);

}