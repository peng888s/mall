package com.bdqn.mall.service.impl;

import com.bdqn.mall.dao.PropertyMapper;
import com.bdqn.mall.entity.Property;
import com.bdqn.mall.service.PropertyService;
import com.bdqn.mall.util.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Resource
    private PropertyMapper propertyMapper;

    public void setPropertyMapper(PropertyMapper propertyMapper) {
        this.propertyMapper = propertyMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean add(Property property) {
        return propertyMapper.insertOne(property)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean addList(List<Property> propertyList) {
        return propertyMapper.insertList(propertyList) > 0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean update(Property property) {
        return propertyMapper.updateOne(property)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean deleteList(Integer[] propertyIdList) {
        return propertyMapper.deleteList(propertyIdList)>0;
    }

    @Override
    public List<Property> getList(Property property, PageUtil pageUtil) {
        return propertyMapper.select(property,pageUtil);
    }

    @Override
    public Property get(Integer propertyId) {
        return propertyMapper.selectOne(propertyId);
    }

    @Override
    public Integer getTotal(Property property) {
        return propertyMapper.selectTotal(property);
    }
}
