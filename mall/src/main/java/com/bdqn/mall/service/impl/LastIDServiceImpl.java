package com.bdqn.mall.service.impl;

import com.bdqn.mall.dao.LastIDMapper;
import com.bdqn.mall.service.LastIDService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LastIDServiceImpl implements LastIDService {
    @Resource
    private LastIDMapper lastIDMapper;


    public void setLastIDMapper(LastIDMapper lastIDMapper) {
        this.lastIDMapper = lastIDMapper;
    }

    @Override
    public int selectLastID() {
        return lastIDMapper.selectLastID();
    }
}
