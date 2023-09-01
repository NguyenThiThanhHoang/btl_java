/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apjob.repository;

import com.apjob.pojo.CV;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface CVRepository {
    List<CV> getCVs(Map<String, String> params);
    int countCVs();
    boolean addOrUpdateTag(CV c);
    CV getCVById(int id);
    boolean deleteCV(int id);
}
