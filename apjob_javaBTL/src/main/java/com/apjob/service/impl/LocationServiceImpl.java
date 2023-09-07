/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.service.impl;

import com.apjob.pojo.Location;
import com.apjob.repository.LocationRepository;
import com.apjob.service.LocationService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class LocationServiceImpl implements LocationService{

    @Autowired
    private LocationRepository locationRepo;
    
    @Override
    public List<Location> getLocations(Map<String, String> params) {
        return this.locationRepo.getLocations(params);
    }
    
}
