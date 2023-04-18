package com.bdqn.mall.service.impl;

import com.bdqn.mall.dao.ProductOrderMapper;
import com.bdqn.mall.entity.OrderGroup;
import com.bdqn.mall.entity.ProductOrder;
import com.bdqn.mall.service.ProductOrderService;
import com.bdqn.mall.util.OrderUtil;
import com.bdqn.mall.util.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {
    @Resource
    private ProductOrderMapper productOrderMapper;

    public void setProductOrderMapper(ProductOrderMapper productOrderMapper) {
        this.productOrderMapper = productOrderMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean add(ProductOrder productOrder) {
        return productOrderMapper.insertOne(productOrder)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean update(ProductOrder productOrder) {
        return productOrderMapper.updateOne(productOrder)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean deleteList(Integer[] productOrderIdList) {
        return productOrderMapper.deleteList(productOrderIdList)>0;
    }

    @Override
    public List<ProductOrder> getList(ProductOrder productOrder, Byte[] productOrderStatusArray, OrderUtil orderUtil, PageUtil pageUtil) {
        return productOrderMapper.select(productOrder,productOrderStatusArray,orderUtil,pageUtil);
    }

    @Override
    public List<OrderGroup> getTotalByDate(Date beginDate, Date endDate) {
        return productOrderMapper.getTotalByDate(beginDate,endDate);
    }

    @Override
    public ProductOrder get(Integer productOrderId) {
        return productOrderMapper.selectOne(productOrderId);
    }

    @Override
    public ProductOrder getByCode(String productOrderCode) {
        return productOrderMapper.selectByCode(productOrderCode);
    }

    @Override
    public Integer getTotal(ProductOrder productOrder, Byte[] productOrderStatusArray) {
        return productOrderMapper.selectTotal(productOrder,productOrderStatusArray);
    }
}
