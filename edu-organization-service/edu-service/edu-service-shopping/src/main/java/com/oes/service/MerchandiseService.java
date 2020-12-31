package com.oes.service;


import com.oes.dao.MerchandiseDao;
import com.oes.entity.Merchandise;
import com.oes.entity.OrderList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author dxh924
 * @Title:
 * @Package
 * @Description:
 * @date 2020/9/219:31
 */
@Service
public class MerchandiseService {
    @Autowired
    private MerchandiseDao merchandiseDao;
    @Autowired
    private OrderListService orderListService;

    public List<Merchandise> findMerchandiseByType(String merType) {
        return merchandiseDao.findMerchandiseByType(merType);
    }

    public Merchandise findMerchandiseById(Integer merId) {
        return merchandiseDao.findMerchandiseById(merId);
    }

    public List<Merchandise> findMerchandiseByState(Integer merState) {
        return merchandiseDao.findMerchandiseByState(merState);
    }

    public int putOnMerchandise(Integer merId) {
        return merchandiseDao.putOnMerchandise(merId);
    }

    public int pullOffMerchandise(Integer merId) {
        return merchandiseDao.pullOffMerchandise(merId);
    }

    public int reduceStock(Integer merId) {
        return merchandiseDao.reduceStock(merId);
    }

    @Transactional
    public boolean increaseStock(List<OrderList> orderLists) {
        if (orderLists == null) {
            return false;
        }
        for (OrderList ol : orderLists) {
            if (merchandiseDao.increaseStock(ol.getMerId()) == 0 ) {
                return false;
            }
        }
        return true;
    }
}
