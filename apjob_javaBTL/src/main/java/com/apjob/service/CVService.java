/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apjob.service;

import com.apjob.pojo.CV;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ASUS
 */
public interface CVService {
    List<CV> getCVs(int candidateId);

    CV addOrUpdateCV(Map<String, String> params, @RequestPart MultipartFile avatar);
}
