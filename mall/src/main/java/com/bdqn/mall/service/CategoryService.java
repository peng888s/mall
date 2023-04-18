package com.bdqn.mall.service;


import com.bdqn.mall.entity.Category;
import com.bdqn.mall.util.PageUtil;

import java.util.List;

public interface CategoryService {
    boolean add(Category category);
    boolean update(Category category);

    List<Category> getList(String categoryName, PageUtil pageUtil);
    Category get(Integer categoryId);
    Integer getTotal(String categoryName);
}
