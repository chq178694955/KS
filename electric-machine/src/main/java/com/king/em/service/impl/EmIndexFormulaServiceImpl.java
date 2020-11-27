package com.king.em.service.impl;

import com.king.em.dao.EmIndexFormulaDao;
import com.king.em.entity.EmIndexFormula;
import com.king.em.service.IEmIndexFormulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/11/26
 * @描述
 */
@Service
public class EmIndexFormulaServiceImpl implements IEmIndexFormulaService {

    @Autowired
    private EmIndexFormulaDao emIndexFormulaDao;

    @Override
    public List<EmIndexFormula> findAll() {
        return emIndexFormulaDao.findAll();
    }
}
