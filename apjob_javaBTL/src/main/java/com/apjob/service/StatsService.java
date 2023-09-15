/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apjob.service;

import java.util.List;
import java.util.Map;
import org.cloudinary.json.JSONArray;

/**
 *
 * @author ASUS
 */
public interface StatsService {
    JSONArray statsRevenue(Map<String, String> params);
}
