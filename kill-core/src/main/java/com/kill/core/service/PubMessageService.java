package com.kill.core.service;

import org.springframework.web.multipart.MultipartFile;

public interface PubMessageService {


    void sendSubMessage(String params);

    /**
     * 获取微信token
     */
    String getAuthToken() throws Exception;


    String importHospitalExcelFile(MultipartFile file, String schoolName);

    String createOrderNumber();
}
