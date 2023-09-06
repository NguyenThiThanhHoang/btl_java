/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apjob.repository;

import com.apjob.pojo.User;


/**
 *
 * @author ASUS
 */
public interface UserRepository {
    int countUsers();
    boolean addOrUpdateUser(User u);
    User getUserById(int id);
    boolean deleteUser(int id);
}
