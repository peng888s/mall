package com.bdqn.mall.service;


import com.bdqn.mall.entity.Property;
import com.bdqn.mall.util.PageUtil;

import java.util.List;

public interface PropertyService {
    boolean add(Property property);

    boolean addList(List<Property> propertyList);
    boolean update(Property property);
    boolean deleteList(Integer[] propertyIdList);

    List<Property> getList(Property property, PageUtil pageUtil);
    Property get(Integer propertyId);
    Integer getTotal(Property property);
}
