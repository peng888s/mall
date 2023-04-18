package com.bdqn.mall.service;

import com.bdqn.mall.entity.ProductImage;
import com.bdqn.mall.util.PageUtil;

import java.util.List;

public interface ProductImageService {
    boolean add(ProductImage productImage);

    boolean addList(List<ProductImage> productImageList);
    boolean update(ProductImage productImage);
    boolean deleteList(Integer[] productImageIdList);

    List<ProductImage> getList(Integer productId, Byte productImageType, PageUtil pageUtil);
    ProductImage get(Integer productImageId);
    Integer getTotal(Integer productId, Byte productImageType);
}
