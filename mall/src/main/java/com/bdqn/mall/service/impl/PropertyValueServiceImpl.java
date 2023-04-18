package com.bdqn.mall.service.impl;

import com.bdqn.mall.dao.PropertyValueMapper;
import com.bdqn.mall.entity.PropertyValue;
import com.bdqn.mall.service.PropertyValueService;
import com.bdqn.mall.util.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PropertyValueServiceImpl implements PropertyValueService {
    @Resource
    private PropertyValueMapper propertyValueMapper;

    public void setPropertyValueMapper(PropertyValueMapper propertyValueMapper) {
        this.propertyValueMapper = propertyValueMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean add(PropertyValue propertyValue) {
        return propertyValueMapper.insertOne(propertyValue)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean addList(List<PropertyValue> propertyValueList) {
        return propertyValueMapper.insertList(propertyValueList) > 0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean update(PropertyValue propertyValue) {
        return propertyValueMapper.updateOne(propertyValue)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean deleteList(Integer[] propertyValueIdList) {
        return propertyValueMapper.deleteList(propertyValueIdList)>0;
    }

    @Override
    public List<PropertyValue> getList(PropertyValue propertyValue, PageUtil pageUtil) {
        return propertyValueMapper.select(propertyValue,pageUtil);
    }

    @Override
    public PropertyValue get(Integer propertyValueId) {
        return propertyValueMapper.selectOne(propertyValueId);
    }

    @Override
    public Integer getTotal(PropertyValue propertyValue) {
        return propertyValueMapper.selectTotal(propertyValue);
    }
}
