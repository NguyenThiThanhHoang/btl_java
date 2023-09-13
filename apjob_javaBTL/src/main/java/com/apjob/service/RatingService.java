/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apjob.service;

import com.apjob.pojo.Rating;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface RatingService {

    List<Rating> getRatings(int companyId);

    Rating addRating(Rating r);
}
