package com.bdqn.mall.service;

import com.bdqn.mall.entity.OrderGroup;
import com.bdqn.mall.entity.ProductOrder;
import com.bdqn.mall.util.OrderUtil;
import com.bdqn.mall.util.PageUtil;

import java.util.Date;
import java.util.List;

public interface ProductOrderService {
    boolean add(ProductOrder productOrder);
    boolean update(ProductOrder productOrder);
    boolean deleteList(Integer[] productOrderIdList);

    List<ProductOrder> getList(ProductOrder productOrder, Byte[] productOrderStatusArray, OrderUtil orderUtil, PageUtil pageUtil);

    List<OrderGroup> getTotalByDate(Date beginDate, Date endDate);

    ProductOrder get(Integer productOrderId);
    ProductOrder getByCode(String productOrderCode);
    Integer getTotal(ProductOrder productOrder, Byte[] productOrderStatusArray);
}
