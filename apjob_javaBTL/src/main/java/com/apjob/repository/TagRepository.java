/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apjob.repository;

import com.apjob.pojo.Tag;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface TagRepository {
    List<Tag> getTags(Map<String, String> params);
    int countTags();
    boolean addOrUpdateTag(Tag t);
    Tag getTagById(int id);
    boolean deleteTag(int id);
}
