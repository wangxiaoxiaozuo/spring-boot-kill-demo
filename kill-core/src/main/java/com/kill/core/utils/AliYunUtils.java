package com.kill.core.utils;

import com.baidu.aip.ocr.AipOcr;
import com.baidu.aip.util.Base64Util;
import com.kill.core.constant.ReadExcelPicConstant;
import com.sun.deploy.net.URLEncoder;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;


/**
 * <pre>
 * +--------+---------+-----------+---------+
 * |                                        |
 * +--------+---------+-----------+---------+
 * </pre>
 *
 * @author wangjian
 * @since 2021/01/13 17:09:43
 */
@UtilityClass
@Slf4j
public class AliYunUtils {


    public String getAccessToken() {

        String getAccessTokenUrl = ReadExcelPicConstant.GET_ALIYUN_TOKEN_URL
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ReadExcelPicConstant.API_KEY
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + ReadExcelPicConstant.API_SECRET;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields();
//            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.err.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            JSONObject jsonObject = new JSONObject(result.toString());
            return jsonObject.getString("access_token");
        } catch (Exception e) {
            log.error("获取token失败！");
        }
        return null;
    }


    /**
     * @param filePath
     * @return
     */
    public String readExcel(String filePath) throws JSONException {
        AipOcr client = new AipOcr("23536507", ReadExcelPicConstant.API_KEY, ReadExcelPicConstant.API_SECRET);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
//        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        JSONObject res = client.basicGeneral(filePath, new HashMap<>());
        System.out.println(res.toString(2));
        return null;
    }

    public String readExcelPic(String filePath, String accessToken) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/form";
        try {
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) throws JSONException {
//        String accessToken = getAccessToken();
//        System.out.println(accessToken);
        readExcelPic("/Users/wangjian/Desktop/20210113103447527_0001.jpg", getAccessToken());
    }


}
