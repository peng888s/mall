package com.bdqn.mall.dao;

import com.bdqn.mall.entity.OrderGroup;
import com.bdqn.mall.entity.ProductOrder;
import com.bdqn.mall.util.OrderUtil;
import com.bdqn.mall.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
@Mapper
public interface ProductOrderMapper {
    Integer insertOne(@Param("productOrder") ProductOrder productOrder);
    Integer updateOne(@Param("productOrder") ProductOrder productOrder);
    Integer deleteList(@Param("productOrderIdList") Integer[] productOrderIdList);

    List<ProductOrder> select(@Param("productOrder") ProductOrder productOrder, @Param("productOrderStatusArray") Byte[] productOrderStatusArray, @Param("orderUtil") OrderUtil orderUtil, @Param("pageUtil") PageUtil pageUtil);
    ProductOrder selectOne(@Param("productOrderId") Integer productOrderId);
    ProductOrder selectByCode(@Param("productOrderCode") String productOrderCode);
    Integer selectTotal(@Param("productOrder") ProductOrder productOrder, @Param("productOrderStatusArray") Byte[] productOrderStatusArray);
    List<OrderGroup> getTotalByDate(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate);
}
