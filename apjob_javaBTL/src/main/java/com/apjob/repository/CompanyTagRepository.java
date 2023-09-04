/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apjob.repository;

import com.apjob.pojo.CompanyTag;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface CompanyTagRepository {
    List<CompanyTag> getCompanyTags(Map<String, String> params);
    int countCompanyTags(Map<String, String> params);
    boolean addCompanyTag(CompanyTag c);
    List<CompanyTag> getCompanyTagById(String companyId, String tagId); 
    boolean deleteCompanyTagById(String companyId, String tagId); 
}
