/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apjob.repository;

import com.apjob.pojo.User;
import java.util.List;


/**
 *
 * @author ASUS
 */
public interface UserRepository {
    List<User> getUsers();
    int countUsers();
    boolean addOrUpdateUser(User u);
    User getUserById(int id);
    boolean deleteUser(int id);
    User getUserByEmail(String email);
    
    boolean authUser(String username, String password);

}
