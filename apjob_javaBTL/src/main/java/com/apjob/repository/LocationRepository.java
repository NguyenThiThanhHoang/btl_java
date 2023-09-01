/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apjob.repository;

import com.apjob.pojo.Location;
import com.apjob.pojo.Tag;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface LocationRepository {
    List<Location> getLocations(Map<String, String> params);
    int countLocations();
    boolean addOrUpdateLocation(Location l);
    Location getLocationById(int id);
    boolean deleteLocation(int id);
}
