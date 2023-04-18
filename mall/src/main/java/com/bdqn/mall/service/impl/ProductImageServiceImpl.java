package com.bdqn.mall.service.impl;

import com.bdqn.mall.dao.ProductImageMapper;
import com.bdqn.mall.entity.ProductImage;
import com.bdqn.mall.service.ProductImageService;
import com.bdqn.mall.util.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Resource
    private ProductImageMapper productImageMapper;

    public void setProductImageMapper(ProductImageMapper productImageMapper) {
        this.productImageMapper = productImageMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean add(ProductImage productImage) {
        return productImageMapper.insertOne(productImage)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean addList(List<ProductImage> productImageList) {
        return productImageMapper.insertList(productImageList) > 0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean update(ProductImage productImage) {
        return productImageMapper.updateOne(productImage)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean deleteList(Integer[] productImageIdList) {
        return productImageMapper.deleteList(productImageIdList)>0;
    }

    @Override
    public List<ProductImage> getList(Integer productId, Byte productImageType, PageUtil pageUtil) {
        return productImageMapper.select(productId,productImageType,pageUtil);
    }

    @Override
    public ProductImage get(Integer productImageId) {
        return productImageMapper.selectOne(productImageId);
    }

    @Override
    public Integer getTotal(Integer productId, Byte productImageType) {
        return productImageMapper.selectTotal(productId,productImageType);
    }
}
