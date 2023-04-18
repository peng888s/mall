package com.bdqn.mall.service.impl;

import com.bdqn.mall.dao.ProductOrderItemMapper;
import com.bdqn.mall.entity.OrderGroup;
import com.bdqn.mall.entity.ProductOrderItem;
import com.bdqn.mall.service.ProductOrderItemService;
import com.bdqn.mall.util.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ProductOrderItemServiceImpl implements ProductOrderItemService {
    @Resource
    private ProductOrderItemMapper productOrderItemMapper;

    public void setProductOrderItemMapper(ProductOrderItemMapper productOrderItemMapper) {
        this.productOrderItemMapper = productOrderItemMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean add(ProductOrderItem productOrderItem) {
        return productOrderItemMapper.insertOne(productOrderItem)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean update(ProductOrderItem productOrderItem) {
        return productOrderItemMapper.updateOne(productOrderItem)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean deleteList(Integer[] productOrderItemIdList) {
        return productOrderItemMapper.deleteList(productOrderItemIdList)>0;
    }

    @Override
    public List<ProductOrderItem> getList(PageUtil pageUtil) {
        return productOrderItemMapper.select(pageUtil);
    }

    @Override
    public List<ProductOrderItem> getListByOrderId(Integer orderId, PageUtil pageUtil) {
        return productOrderItemMapper.selectByOrderId(orderId,pageUtil);
    }

    @Override
    public List<ProductOrderItem> getListByUserId(Integer userId, PageUtil pageUtil) {
        return productOrderItemMapper.selectByUserId(userId,pageUtil);
    }

    @Override
    public List<ProductOrderItem> getListByProductId(Integer productId, PageUtil pageUtil) {
        return productOrderItemMapper.selectByProductId(productId,pageUtil);
    }

    @Override
    public ProductOrderItem get(Integer productOrderItemId) {
        return productOrderItemMapper.selectOne(productOrderItemId);
    }

    @Override
    public Integer getTotal() {
        return productOrderItemMapper.selectTotal();
    }

    @Override
    public Integer getTotalByOrderId(Integer orderId) {
        return productOrderItemMapper.selectTotalByOrderId(orderId);
    }

    @Override
    public Integer getTotalByUserId(Integer userId) {
        return productOrderItemMapper.selectTotalByUserId(userId);
    }

    @Override
    public List<OrderGroup> getTotalByProductId(Integer productId, Date beginDate, Date endDate) {
        return productOrderItemMapper.getTotalByProductId(productId,beginDate,endDate);
    }

    @Override
    public Integer getSaleCountByProductId(Integer productId) {
        return productOrderItemMapper.selectSaleCount(productId);
    }
}
