package com.bdqn.mall.service;


import com.bdqn.mall.entity.PropertyValue;
import com.bdqn.mall.util.PageUtil;

import java.util.List;

public interface PropertyValueService {
    boolean add(PropertyValue propertyValue);

    boolean addList(List<PropertyValue> propertyValueList);
    boolean update(PropertyValue propertyValue);
    boolean deleteList(Integer[] propertyValueIdList);

    List<PropertyValue> getList(PropertyValue propertyValue, PageUtil pageUtil);
    PropertyValue get(Integer propertyValueId);
    Integer getTotal(PropertyValue propertyValue);
}
