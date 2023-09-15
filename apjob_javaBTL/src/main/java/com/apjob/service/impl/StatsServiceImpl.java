/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.service.impl;

import com.apjob.repository.StatsRepository;
import com.apjob.service.StatsService;
import java.util.List;
import java.util.Map;
import org.cloudinary.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class StatsServiceImpl implements StatsService{
    
    @Autowired
    private StatsRepository sRepo;

    @Override
    public JSONArray statsRevenue(Map<String, String> params) {
        return this.sRepo.statsRevenue(params);
    }
    
}
