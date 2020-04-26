package com.king.keke.dao;

import com.king.keke.entity.Resident;
import java.util.List;

public interface ResidentMapper {
    int insert(Resident record);

    List<Resident> selectAll();
}