package com.hopu.service;

import com.hopu.pojo.Region;

import java.util.List;

public interface RegionService {
    List<Region> findAll();

    Region findById(Integer regionId);
}
