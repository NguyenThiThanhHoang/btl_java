/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apjob.service;

import com.apjob.pojo.Tag;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface TagService {
    List<Tag> getTags(Map<String, String> params);
}
