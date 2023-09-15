/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.pojo;

import java.util.Collection;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author ASUS
 */
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private Set<GrantedAuthority> authorities;
    private String fullName;
    private String phone;
    private String email;
    private String avatar;

    public CustomUserDetails(User u, Set<GrantedAuthority> authorities) {
        this.username = u.getEmail();
        this.password = u.getPassword();
        this.authorities = authorities;
        this.fullName = u.getName();
        this.email = u.getEmail();
        this.phone = u.getPhone();
        this.avatar = u.getAvatar();
    }

    public CustomUserDetails(User u) {
        this.username = u.getEmail();
        this.password = u.getPassword();
        this.fullName = u.getName();
        this.email = u.getEmail();
        this.phone = u.getPhone();
        this.avatar = u.getAvatar();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Hoặc thực hiện kiểm tra theo yêu cầu
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Hoặc thực hiện kiểm tra theo yêu cầu
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Hoặc thực hiện kiểm tra theo yêu cầu
    }

    @Override
    public boolean isEnabled() {
        return true; // Hoặc thực hiện kiểm tra theo yêu cầu
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
