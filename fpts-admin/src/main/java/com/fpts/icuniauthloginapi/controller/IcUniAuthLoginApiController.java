package com.fpts.icuniauthloginapi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fpts.common.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class IcUniAuthLoginApiController extends BaseController
{
    /**
     * icUniAuth 发给 FPTS 的 client_id
     */
    private final String clientId = "bkKPzvYFRLOXym9KbbVqCM9omXdV1n1C";

    /**
     * icUniAuth 发给 FPTS 的 client_secret
     */
    private final String clientSecret = "v0WhArmXnLkV0iBEddAwQvEnamtBi2Et";

    /**
     * 在 icUniAuth 处注册时提供给 icUniAuth 的 redirect_uri
     */
    private final String redirectUri = "http://localhost:80/icuniauth_client";

    /**
     * 请求的数据范围
     * B: Basic info(昵称和 OpenID)
     * E: Email
     */
    private final String scope = "BE";

    /**
     * Method to encode a string value using `UTF-8` encoding scheme
     * @param value string to be encoded
     * @return url-encoded string
     */
    private static String urlEncodedValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }

    /**
     * 统一认证登录，获取用户授权
     */
    @GetMapping("/icuniauth_login")
    public String authGrant()
    {
        String baseUrl = "http://uniauth.icseclab.org/api/authorize?";
        String params = "response_type=code&client_id=" + clientId + "&state=requesting_for_code" +
                "&redirect_uri=" + urlEncodedValue(redirectUri) + "&scope=" + scope;
        String url = baseUrl + params;

        // 重定向至 client endpoint
        return "redirect:" + url;
    }

    /**
     * 获取 auth code，接着获取 access token 和 refresh token，然后登录
     * @param error 如果出错，这个参数不为 null
     * @param code 如果正确，这个参数不为 null
     */
    @GetMapping("/icuniauth_client")
    public String loginWithIcUniAuth(@RequestParam(value = "error", required = false, defaultValue = "null") String error,
                            @RequestParam(value = "code", required = false, defaultValue = "null") String code)
    {
        // 出错了
        if (!error.equals("null")) return "redirect:login";

        // 没有出错，继续。
        // 尝试读取 code，并发送 token request
        if (!code.equals("null"))
        {
            String authCode = code;
            System.out.println("芝士 code：" + authCode);
            // 获取 access token 和 refresh token
            try
            {
                // 新建连接
                URL url = new URL("http://uniauth.icseclab.org/api/token");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                // 设置请求类型
                connection.setRequestMethod("POST");
                // 设置 content-type
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                // 其他设置
                connection.setDoInput(true);
                connection.setDoOutput(true);
                // entity-body 的内容
                String entityBody = "grant_type=authorization_code&code=" + authCode + "&redirect_url=" + urlEncodedValue(redirectUri) +
                        "&client_id=" + clientId + "&client_secret=" + clientSecret;
                System.out.println("entity-body: " + entityBody);
                // 写入 http request body
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
                writer.write(entityBody);
                // 发送
                writer.flush();
                // 关闭 writer
                writer.close();

                // 获取 access token
                // 读取输入流
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                StringBuffer stringBuffer = new StringBuffer();
                String stringRead = null;
                while ((stringRead = reader.readLine()) != null) stringBuffer.append(stringRead);
                reader.close();
                // 关闭 input stream
                inputStream.close();
                // 关闭连接
                connection.disconnect();
                System.out.println("获取到的数据：" + stringBuffer.toString());
                // 把结果转化为 json
                JSONObject responseBody = (JSONObject) JSON.parse(stringBuffer.toString());
                // 获取 access token
                String accessToken = responseBody.getString("access_token");
                System.out.println("access token: " + accessToken);
                // 获取 access token 的 expire time
                String expireTime = responseBody.getString("expires_in");
                System.out.println("expire time: " + expireTime);
                // 获取 refresh token
                String refreshToken = responseBody.getString("refresh_token");
                System.out.println("refresh token: " + refreshToken);

                // 获取用户资料
                // 设置 url
                url = new URL("http://uniauth.icseclab.org/api/get_user_info");
                // 搭建连接
                connection = (HttpURLConnection) url.openConnection();
                // 其他设置
                connection.setDoInput(true);
                connection.setDoOutput(true);
                // 设置请求类型
                connection.setRequestMethod("POST");
                // 设置 content-type
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                // 写 entity-body
                entityBody = "access_token=" + accessToken;
                System.out.println("entity-body: " + entityBody);
                // 写入 http request body
                writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
                writer.write(entityBody);
                // 发送
                writer.flush();
                // 关闭
                writer.close();

                // 获取用户资源
                // 读取输入流
                inputStream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                stringBuffer = new StringBuffer();
                stringRead = null;
                while ((stringRead = reader.readLine()) != null) stringBuffer.append(stringRead);
                reader.close();
                // 关闭连接
                connection.disconnect();
                System.out.println("获取到的数据：" + stringBuffer.toString());
                // 把结果转化为 json
                responseBody = (JSONObject) JSON.parse(stringBuffer.toString());
                System.out.println("json 数据：" + responseBody.toString());
                // 获取 data 部分
                JSONObject data = responseBody.getJSONObject("data");
                System.out.println("data：" + data.toString());
                // 获取 nickname
                String nickName = data.getString("nick_name");
                System.out.println("nickname: " + nickName);
                // 获取 open id
                String openId = data.getString("open_id");
                System.out.println("open id: " + openId);
                // 获取 email
                String email = data.getString("email");
                System.out.println("email: " + email);

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            // 留在本页
            return "icuniauth_client";
        }

        // 尝试读取

        return "redirect:index";
    }

}
