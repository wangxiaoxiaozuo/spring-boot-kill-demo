package com.kill.core.constant;

import cn.hutool.core.util.StrUtil;
import org.springframework.web.client.RestTemplate;

/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2021/02/19 15:51:31
 */
public class PushMessageConstant {

    /**
     * 发送消息地址
     */
    public static final String SEND_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token={}";

    /**
     * 获取token地址
     */
    public static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={}&secret={}";

    /**
     * appid
     */
//    public static final String APPID = "wx208109099b862b63";
    public static final String APPID = "wx9baaba7d1cdb3549";

    /**
     * app_secret
     */
    public static final String APP_SECRET = "15810c7c7b4ae938453b85563efb1bb6";

}
