package com.kill.core.service;

public interface PubMessageService {


    void sendSubMessage(String params);

    /**
     * 获取微信token
     */
    String getAuthToken() throws Exception;


}
