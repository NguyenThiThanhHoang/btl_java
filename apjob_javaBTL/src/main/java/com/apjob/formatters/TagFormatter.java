/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apjob.formatters;

import com.apjob.pojo.Tag;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author ASUS
 */
public class TagFormatter implements Formatter<Tag>{

    @Override
    public String print(Tag t, Locale locale) {
        return String.valueOf(t.getId());
    }

    @Override
    public Tag parse(String tagId, Locale locale) throws ParseException {
        int id = Integer.parseInt(tagId);
        return new Tag(id);
    }
    
}
