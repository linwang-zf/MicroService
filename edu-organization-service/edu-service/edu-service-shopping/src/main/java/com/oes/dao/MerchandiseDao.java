package com.oes.dao;


import com.oes.entity.Merchandise;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MerchandiseDao {
    int insert(Merchandise record);

    int insertSelective(Merchandise record);

    List<Merchandise> findMerchandiseByType(String merType);

    Merchandise findMerchandiseById(Integer merId);

    List<Merchandise> findMerchandiseByState(Integer merState);

    int putOnMerchandise(Integer merId);

    int pullOffMerchandise(Integer merId);

    int reduceStock(Integer merId);

    int increaseStock(Integer merId);
}