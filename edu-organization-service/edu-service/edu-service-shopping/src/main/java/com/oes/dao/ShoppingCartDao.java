package com.oes.dao;


import com.oes.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShoppingCartDao {
    int insert(ShoppingCart record);

    int insertSelective(ShoppingCart record);

    List<ShoppingCart> findAllByUserId(Integer userId);

    List<ShoppingCart> findByUserId(Integer userId, Integer state);

    int selectAll(Integer userId);

    int unSelectAll(Integer userId);

    int selectIt(Integer userId, Integer merId);

    int unSelectIt(Integer userId, Integer merId);

    int delete(Integer userId, Integer merId);

    ShoppingCart isExisted(Integer userId, Integer merId);
}