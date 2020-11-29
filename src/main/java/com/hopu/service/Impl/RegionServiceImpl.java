package com.hopu.service.Impl;


import com.hopu.mapper.RegionMapper;
import com.hopu.pojo.Region;
import com.hopu.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionMapper regionMapper;
    @Override
    public List<Region> findAll() {
        return regionMapper.findAll();
    }

    @Override
    public Region findById(Integer regionId) {
        return regionMapper.findById(regionId);
    }
}
