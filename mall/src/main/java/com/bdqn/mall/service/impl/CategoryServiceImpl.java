package com.bdqn.mall.service.impl;

import com.bdqn.mall.dao.CategoryMapper;
import com.bdqn.mall.entity.Category;
import com.bdqn.mall.service.CategoryService;
import com.bdqn.mall.util.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean add(Category category) {
        return categoryMapper.insertOne(category)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean update(Category category) {
        return categoryMapper.updateOne(category)>0;
    }

    @Override
    public List<Category> getList(String categoryName, PageUtil pageUtil) {
        return categoryMapper.select(categoryName,pageUtil);
    }

    @Override
    public Category get(Integer categoryId) {
        return categoryMapper.selectOne(categoryId);
    }

    @Override
    public Integer getTotal(String categoryName) {
        return categoryMapper.selectTotal(categoryName);
    }
}
