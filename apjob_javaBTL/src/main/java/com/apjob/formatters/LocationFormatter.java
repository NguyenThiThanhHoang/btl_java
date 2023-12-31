/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.formatters;

import com.apjob.pojo.Location;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author ASUS
 */
public class LocationFormatter implements Formatter<Location>{

    @Override
    public String print(Location l, Locale locale) {
        return String.valueOf(l.getId());
    }

    @Override
    public Location parse(String location, Locale locale) throws ParseException {
        int id = Integer.parseInt(location);
        return new Location(id);
    }
    
}
