package com.bdqn.mall.service;

import com.bdqn.mall.entity.Product;
import com.bdqn.mall.util.OrderUtil;
import com.bdqn.mall.util.PageUtil;
import java.util.List;

public interface ProductService {
    boolean add(Product product);
    boolean update(Product product);

    List<Product> getList(Product product, Byte[] productIsEnabledArray, OrderUtil orderUtil, PageUtil pageUtil);

    List<Product> getTitle(Product product, PageUtil pageUtil);
    Product get(Integer productId);
    Integer getTotal(Product product, Byte[] productIsEnabledArray);

    List<Product> getMoreList(Product product, Byte[] bytes, OrderUtil orderUtil, PageUtil pageUtil, String[] productNameSplit);

    Integer getMoreListTotal(Product product, Byte[] bytes, String[] productNameSplit);
}
