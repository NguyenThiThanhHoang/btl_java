/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apjob.repository;

import com.apjob.pojo.Rating;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface RatingRepository {
    List<Rating> getRatings(Map<String, String> params);
    int countRatings();
    boolean addOrUpdateRating(Rating r);
    Rating getRatingById(int id);
    boolean deleteRating(int id);
}
