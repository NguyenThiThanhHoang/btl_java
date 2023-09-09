/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apjob.repository;

import com.apjob.pojo.Company;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface CompanyRepository {
    List<Company> getCompanys(Map<String, String> params);
    int countCompanys();
    boolean addOrUpdateCompany(Company c);
    Company getCompanyById(int id);
    boolean deleteCompany(int id);
    Company getCompanyByEmail(String email);
    Company getCompanyByTax(int tax);
}
