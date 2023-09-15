/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apjob.repository;

import com.apjob.pojo.Employer;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public interface EmployerRepository {

    List<Employer> getEmployers(Map<String, String> params);

    int countEmployers();

    boolean addOrUpdateEmployer(Employer e);

    Employer getEmployerById(int id);

    boolean deleteEmployer(int id);

    List<Employer> getEmployersFalse(Map<String, String> params);

    int countEmployersFalse();
}
