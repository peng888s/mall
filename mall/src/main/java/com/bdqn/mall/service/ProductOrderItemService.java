package com.bdqn.mall.service;

import com.bdqn.mall.entity.OrderGroup;
import com.bdqn.mall.entity.ProductOrderItem;
import com.bdqn.mall.util.PageUtil;

import java.util.Date;
import java.util.List;

public interface ProductOrderItemService {
    boolean add(ProductOrderItem productOrderItem);
    boolean update(ProductOrderItem productOrderItem);
    boolean deleteList(Integer[] productOrderItemIdList);

    List<ProductOrderItem> getList(PageUtil pageUtil);
    List<ProductOrderItem> getListByOrderId(Integer orderId, PageUtil pageUtil);
    List<ProductOrderItem> getListByUserId(Integer userId, PageUtil pageUtil);
    List<ProductOrderItem> getListByProductId(Integer productId, PageUtil pageUtil);
    ProductOrderItem get(Integer productOrderItemId);
    Integer getTotal();
    Integer getTotalByOrderId(Integer orderId);
    Integer getTotalByUserId(Integer userId);

    List<OrderGroup> getTotalByProductId(Integer productId, Date beginDate, Date endDate);

    Integer getSaleCountByProductId(Integer productId);
}
