package com.fpts.icuniauthloginapi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fpts.common.core.controller.BaseController;
import com.fpts.common.core.domain.entity.SysUser;
import com.fpts.framework.shiro.uniauthtoken.LoginType;
import com.fpts.framework.shiro.uniauthtoken.ExtendedUsernamePasswordToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fpts.common.annotation.Log;
import com.fpts.common.enums.BusinessType;
import com.fpts.icuniauthloginapi.domain.IcUniAuthLoginApiInfo;
import com.fpts.icuniauthloginapi.service.IIcUniAuthLoginApiInfoService;
import com.fpts.common.core.domain.AjaxResult;
import com.fpts.common.utils.poi.ExcelUtil;
import com.fpts.common.core.page.TableDataInfo;
import com.fpts.framework.shiro.service.SysRegisterService;
import com.fpts.system.service.ISysUserService;

@Controller
public class IcUniAuthLoginApiController extends BaseController
{
    @Autowired
    private IIcUniAuthLoginApiInfoService externalUserInfoService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private SysRegisterService registerService;

    private String prefix = "icuniauth_login";
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
    private final String redirectUri = "http://47.108.114.204:8888/icuniauth_client";

    /**
     * 请求的数据范围
     * B: Basic info(昵称和 OpenID)
     * E: Email
     */
    private final String scope = "BE";

    /**
     * 获取 access token & refresh token 的接口
     */
    private final String tokenEndpoint = "http://uniauth.icseclab.org/api/token";

    /**
     * 获取 access token 后，获取资源的接口
     */
    private final String resourceEndpoint = "http://uniauth.icseclab.org/api/get_user_info";

    /**
     * icUniAuth本系统内的ID
     * 因为本系统可能支持多个统一认证登录，因此需要给这些登录系统一个ID
     */
    private final String appId = "mmuN1G9np9lzFU4SynnbFX46yoRzvwF3";

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
            // System.out.println("芝士 code：" + authCode);
            // 获取 access token 和 refresh token
            try
            {
                // 请求 tokens
                String entityBody = "grant_type=authorization_code&code=" + authCode + "&redirect_url=" + urlEncodedValue(redirectUri) +
                        "&client_id=" + clientId + "&client_secret=" + clientSecret;
                JSONObject responseBody = sendHttpPostRequest(tokenEndpoint, entityBody);
                // System.out.println("json 数据：" + responseBody.toString());
                // 获取 access token
                String accessToken = responseBody.getString("access_token");
                System.out.println("access token: " + accessToken);
                // 获取 access token 的 expire time
                String expireTime = responseBody.getString("expires_in");
                // System.out.println("expire time: " + expireTime);
                // 获取 refresh token
                String refreshToken = responseBody.getString("refresh_token");
                System.out.println("refresh token: " + refreshToken);

                // 更改 entity body
                entityBody = "access_token=" + accessToken;
                // 请求用户资料
                responseBody = sendHttpPostRequest(resourceEndpoint, entityBody);
                System.out.println("json 数据：" + responseBody.toString());
                // 获取 data 部分
                JSONObject data = responseBody.getJSONObject("data");
                // System.out.println("data：" + data.toString());
                // 获取 nickname
                String nickName = data.getString("nick_name");
                // System.out.println("nickname: " + nickName);
                // 获取 open id
                String openId = data.getString("open_id");
                // System.out.println("open id: " + openId);
                // 获取 email
                String email = data.getString("email");
                // System.out.println("email: " + email);

                // 检查是否为新用户，为新用户自动注册
                List<IcUniAuthLoginApiInfo> resultList = selectUserByOpenIdAndAppId(openId, appId);
                if (resultList.isEmpty())
                {
                    // 首先写入 sys_user 表
                    SysUser newUser = new SysUser();
                    // 新用户信息
                    newUser.setPassword(getRandomString(6));
                    newUser.setUserName(nickName);
                    newUser.setLoginName(nickName);
                    newUser.setEmail(email);
                    // System.out.println(newUser.toString());
                    // 注册用户
                    String msg = registerService.register(newUser);
                    // System.out.println("注册结果：" + msg);
                    // 获取用户在 sys_user 表中的 id
                    Long userId = sysUserService.selectUserByLoginName(nickName).getUserId();
                    // 然后用户信息写入临时对象
                    IcUniAuthLoginApiInfo externalUser = new IcUniAuthLoginApiInfo(userId, accessToken, refreshToken,
                            appId, "icUniAuth", nickName, email, openId);
                    // 写入 external_user 表
                    externalUserInfoService.insertExternalUserInfo(externalUser);
                }
                else // 为老用户更新 access token 和 refresh token
                {
                    // tokens 写入临时对象
                    IcUniAuthLoginApiInfo externalUser = new IcUniAuthLoginApiInfo();
                    externalUser.setAccessToken(accessToken);
                    externalUser.setRefreshToken(refreshToken);
                    externalUser.setUserId(resultList.get(0).getUserId());
                    // 写入 external_user 表
                    externalUserInfoService.updateExternalUserInfo(externalUser);
                }

                // 登录
                ExtendedUsernamePasswordToken token = new ExtendedUsernamePasswordToken(nickName, LoginType.NOPASSWD);
                Subject subject = SecurityUtils.getSubject();
                subject.login(token);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return "redirect:login";
            }
        }
        return "redirect:index";
    }

    public JSONObject sendHttpPostRequest(String serverUrl, String entityBody) throws Exception
    {
        JSONObject responseBody = null;
        try
        {
            // 新建连接
            URL url = new URL(serverUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置请求类型
            connection.setRequestMethod("POST");
            // 设置 content-type
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 其他设置
            connection.setDoInput(true);
            connection.setDoOutput(true);
            // System.out.println("entity-body: " + entityBody);
            // 写入 http request body
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8);
            writer.write(entityBody);
            // 发送
            writer.flush();
            // 关闭 writer
            writer.close();

            // 获取 access token
            // 读取输入流
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            StringBuffer stringBuffer = new StringBuffer();
            String stringRead = null;
            while ((stringRead = reader.readLine()) != null) stringBuffer.append(stringRead);
            reader.close();
            // 关闭 input stream
            inputStream.close();
            // 关闭连接
            connection.disconnect();
            // System.out.println("获取到的数据：" + stringBuffer.toString());
            // 把结果转化为 json
            responseBody = (JSONObject) JSON.parse(stringBuffer.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception("access denied!");
        }

        return responseBody;
    }

    /**
     * 检验从统一认证系统登录的用户是否已经在本系统内注册过
     * @param openId 统一认证登录系统提供的用户在认证系统内部的ID
     * @return true-注册过，false-没注册过
     */
    public List<IcUniAuthLoginApiInfo> selectUserByOpenIdAndAppId(String openId, String appId)
    {
        // 新建用户对象，该对象是要查找的用户
        IcUniAuthLoginApiInfo userInfo = new IcUniAuthLoginApiInfo();
        userInfo.setOpenId(openId);
        userInfo.setAppId(appId);
        // 返回结果
        return externalUserInfoService.selectExternalUserInfoList(userInfo);
    }

    /**
     * 生成随机字符串，字符串包含字符 a-z & A-Z & 0-9
     * @param length 生成的字符串的长度
     * @return 随机字符串
     */
    public static String getRandomString(int length)
    {
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    @RequiresPermissions("system:info:view")
    @GetMapping()
    public String info()
    {
        return prefix + "/info";
    }

    /**
     * 查询外部用户，用于存储从其他应用登录FPTS的用户的原始信息列表
     */
    @RequiresPermissions("system:info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(IcUniAuthLoginApiInfo externalUserInfo)
    {
        startPage();
        List<IcUniAuthLoginApiInfo> list = externalUserInfoService.selectExternalUserInfoList(externalUserInfo);
        return getDataTable(list);
    }

    /**
     * 导出外部用户，用于存储从其他应用登录FPTS的用户的原始信息列表
     */
    @RequiresPermissions("system:info:export")
    @Log(title = "外部用户，用于存储从其他应用登录FPTS的用户的原始信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(IcUniAuthLoginApiInfo externalUserInfo)
    {
        List<IcUniAuthLoginApiInfo> list = externalUserInfoService.selectExternalUserInfoList(externalUserInfo);
        ExcelUtil<IcUniAuthLoginApiInfo> util = new ExcelUtil<IcUniAuthLoginApiInfo>(IcUniAuthLoginApiInfo.class);
        return util.exportExcel(list, "外部用户，用于存储从其他应用登录FPTS的用户的原始信息数据");
    }

    /**
     * 新增外部用户，用于存储从其他应用登录FPTS的用户的原始信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存外部用户，用于存储从其他应用登录FPTS的用户的原始信息
     */
    @RequiresPermissions("system:info:add")
    @Log(title = "外部用户，用于存储从其他应用登录FPTS的用户的原始信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(IcUniAuthLoginApiInfo externalUserInfo)
    {
        return toAjax(externalUserInfoService.insertExternalUserInfo(externalUserInfo));
    }

    /**
     * 修改外部用户，用于存储从其他应用登录FPTS的用户的原始信息
     */
    @RequiresPermissions("system:info:edit")
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        IcUniAuthLoginApiInfo externalUserInfo = externalUserInfoService.selectExternalUserInfoByUserId(userId);
        mmap.put("externalUserInfo", externalUserInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存外部用户，用于存储从其他应用登录FPTS的用户的原始信息
     */
    @RequiresPermissions("system:info:edit")
    @Log(title = "外部用户，用于存储从其他应用登录FPTS的用户的原始信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(IcUniAuthLoginApiInfo externalUserInfo)
    {
        return toAjax(externalUserInfoService.updateExternalUserInfo(externalUserInfo));
    }

    /**
     * 删除外部用户，用于存储从其他应用登录FPTS的用户的原始信息
     */
    @RequiresPermissions("system:info:remove")
    @Log(title = "外部用户，用于存储从其他应用登录FPTS的用户的原始信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(externalUserInfoService.deleteExternalUserInfoByUserIds(ids));
    }
}
