package com.bdqn.mall.service;


import com.bdqn.mall.entity.Address;

import java.util.List;

public interface AddressService {
    boolean add(Address address);
    boolean update(Address address);

    List<Address> getList(String addressName, String addressRegionId);
    Address get(String addressAreaId);
    List<Address> getRoot();
}
