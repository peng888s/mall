package com.bdqn.mall.service.impl;

import com.bdqn.mall.dao.ProductMapper;
import com.bdqn.mall.entity.Product;
import com.bdqn.mall.service.ProductService;
import com.bdqn.mall.util.OrderUtil;
import com.bdqn.mall.util.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;

    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean add(Product product) {
        return productMapper.insertOne(product)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean update(Product product) {
        return productMapper.updateOne(product)>0;
    }

    @Override
    public List<Product> getList(Product product, Byte[] productIsEnabledArray, OrderUtil orderUtil, PageUtil pageUtil) {
        return productMapper.select(product, productIsEnabledArray, orderUtil, pageUtil);
    }

    @Override
    public List<Product> getTitle(Product product, PageUtil pageUtil) {
        return productMapper.selectTitle(product, pageUtil);
    }

    @Override
    public Product get(Integer productId) {
        return productMapper.selectOne(productId);
    }

    @Override
    public Integer getTotal(Product product,Byte[] productIsEnabledArray) {
        return productMapper.selectTotal(product,productIsEnabledArray);
    }

    @Override
    public List<Product> getMoreList(Product product, Byte[] bytes, OrderUtil orderUtil, PageUtil pageUtil, String[] productNameSplit) {
        return productMapper.selectMoreList(product, bytes, orderUtil, pageUtil, productNameSplit);
    }

    @Override
    public Integer getMoreListTotal(Product product, Byte[] bytes, String[] productNameSplit) {
        return productMapper.selectMoreListTotal(product, bytes, productNameSplit);
    }
}
