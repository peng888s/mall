package com.bdqn.mall.service.impl;

import com.bdqn.mall.dao.AddressMapper;
import com.bdqn.mall.entity.Address;
import com.bdqn.mall.service.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Resource
    private AddressMapper addressMapper;
    public void setAddressMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean add(Address address) {
        return addressMapper.insertOne(address)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean update(Address address) {
        return addressMapper.updateOne(address)>0;
    }

    @Override
    public List<Address> getList(String addressName, String addressRegionId) {
        return addressMapper.select(addressName,addressRegionId);
    }

    @Override
    public Address get(String addressAreaId) {
        return addressMapper.selectOne(addressAreaId);
    }

    @Override
    public List<Address> getRoot() {
        return addressMapper.selectRoot();
    }
}
