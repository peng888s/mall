package com.bdqn.mall.dao;

import com.bdqn.mall.entity.OrderGroup;
import com.bdqn.mall.entity.ProductOrderItem;
import com.bdqn.mall.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
@Mapper
public interface ProductOrderItemMapper {
    Integer insertOne(@Param("productOrderItem") ProductOrderItem productOrderItem);
    Integer updateOne(@Param("productOrderItem") ProductOrderItem productOrderItem);
    Integer deleteList(@Param("productOrderItemIdList") Integer[] productOrderItemIdList);

    List<ProductOrderItem> select(@Param("pageUtil") PageUtil pageUtil);
    List<ProductOrderItem> selectByOrderId(@Param("orderId") Integer orderId, @Param("pageUtil") PageUtil pageUtil);
    List<ProductOrderItem> selectByUserId(@Param("userId") Integer userId, @Param("pageUtil") PageUtil pageUtil);
    List<ProductOrderItem> selectByProductId(@Param("productId") Integer productId, @Param("pageUtil") PageUtil pageUtil);
    ProductOrderItem selectOne(@Param("productOrderItemId") Integer productOrderItemId);
    Integer selectTotal();
    Integer selectTotalByOrderId(@Param("orderId") Integer orderId);
    Integer selectTotalByUserId(@Param("userId") Integer userId);
    Integer selectSaleCount(@Param("productId") Integer productId);

    List<OrderGroup> getTotalByProductId(@Param("productId") Integer productId, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);
}
