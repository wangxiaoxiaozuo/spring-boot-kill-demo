package com.kill.core.service.impl;

import cn.hutool.core.util.StrUtil;
import com.kill.core.constant.PushMessageConstant;
import com.kill.core.entity.WeChatToken;
import com.kill.core.service.PubMessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2021/02/19 15:49:23
 */
@Service
@Slf4j
@AllArgsConstructor
public class PubMessageServiceImpl implements PubMessageService {


    private RestTemplate restTemplate;


    @Override
    public void sendSubMessage(String openId) {
        String authToken = null;
        try {
            authToken = getAuthToken();
        } catch (Exception e) {
            log.error("获取微信token失败：{}", e.getMessage());
        }
        log.info("获取微信token:{}", authToken);

        String json = "{\"touser\":\"" + openId + "\",\"template_id\":\"MWKPsaBbUFN8WJ1bIjfbVT_qy9fh8sARUP0LM5zoCBg\",\"page\":\"index\",\"data\":{\"name3\":{\"value\":\"王小左\"},\"thing4\":{\"value\":\"这就是一个提示信息\"},\"thing2\":{\"value\":\"多喝热水\"}}}";

        String url = StrUtil.format(PushMessageConstant.SEND_MESSAGE_URL, authToken);
        sendMessage(json, url);

    }

    private void sendMessage(String params, String url) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        HttpEntity<String> formEntity = new HttpEntity<>(params, headers);
        ResponseEntity<String> resultResponseEntity = restTemplate.postForEntity(url, formEntity, String.class);
        log.info("发送订阅消息返回：{}", resultResponseEntity.getBody());
    }

    @Override
    public String getAuthToken() throws Exception {
        String url = StrUtil.format(PushMessageConstant.GET_TOKEN_URL, PushMessageConstant.APPID, PushMessageConstant.APP_SECRET);
        WeChatToken weChatToken = restTemplate.getForObject(url, WeChatToken.class);
        return weChatToken.getAccess_token();
    }

}
