package com.hopu.utils;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import java.util.Map;


public class Message {


    /**
     * 发送短信
     */


    // 设置公共请求参数，初始化Client。
    private DefaultProfile profile = DefaultProfile.getProfile(

            "cn-hangzhou",// API支持的地域ID，如短信API的值为：cn-hangzhou。
            "LTAI4Fyn5DrKbZ6XeHB1wUY4",// 您的AccessKey ID。

            "bv80k7z9Pkxns276etDEJsj5uen6io");// 您的AccessKey Secret。
    private IAcsClient client = new DefaultAcsClient(profile);

    private static void log_print(String functionName, Object result) {
        Gson gson = new Gson();
        System.out.println("-------------------------------" + functionName + "-------------------------------");
        System.out.println(gson.toJson(result));
    }

    /**
     * 添加短信模板
     */
    private String addSmsTemplate() throws ClientException {
        CommonRequest addSmsTemplateRequest = new CommonRequest();
        addSmsTemplateRequest.setSysDomain("dysmsapi.aliyuncs.com");
        addSmsTemplateRequest.setSysAction("AddSmsTemplate");
        addSmsTemplateRequest.setSysVersion("2017-05-25");
        // 短信类型。0：验证码；1：短信通知；2：推广短信；3：国际/港澳台消息
        addSmsTemplateRequest.putQueryParameter("TemplateType", "0");
        // 模板名称，长度为1~30个字符
        addSmsTemplateRequest.putQueryParameter("TemplateName", "测试短信模板");
        // 模板内容，长度为1~500个字符
        addSmsTemplateRequest.putQueryParameter("TemplateContent", "您正在申请手机注册，验证码为：${code}，5分钟内有效！");
        // 短信模板申请说明
        addSmsTemplateRequest.putQueryParameter("Remark", "测试");
        CommonResponse addSmsTemplateResponse = client.getCommonResponse(addSmsTemplateRequest);
        String data = addSmsTemplateResponse.getData();
        // 消除返回文本中的反转义字符
        String sData = data.replaceAll("'\'", "");
        log_print("addSmsTemplate", sData);
        Gson gson = new Gson();
        // 将字符串转换为Map类型，取TemplateCode字段值
        Map map = gson.fromJson(sData, Map.class);
        Object templateCode = map.get("TemplateCode");
        return templateCode.toString();
    }

    /**
     * 发送短信
     */
    public String sendSms(String phoneNumber) throws ClientException {
        //添加短信模板
        addSmsTemplate();
        //随机生成验证码，并返回验证码
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <6 ; i++) {
            int v = (int)(Math.random() * 10);
            sb.append(v);
        }
        //保存随机生成的验证码
        String code = sb.toString();
        //将生成的验证码拼接成json格式
        sb.insert(0, "{'code':'");
        sb.append("'}");
        CommonRequest request = new CommonRequest();
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        // 接收短信的手机号码
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        // 短信签名名称。请在控制台签名管理页面签名名称一列查看（必须是已添加、并通过审核的短信签名）。
        request.putQueryParameter("SignName", "云租房");
        // 短信模板ID
        request.putQueryParameter("TemplateCode", "SMS_205458698");
        // 短信模板变量对应的实际值，JSON格式。
        request.putQueryParameter("TemplateParam", sb.toString());
        CommonResponse commonResponse = client.getCommonResponse(request);
        String data = commonResponse.getData();
        String sData = data.replaceAll("'\'", "");
        log_print("sendSms", sData);
        Gson gson = new Gson();
        Map map = gson.fromJson(sData, Map.class);
        Object bizId = map.get("BizId");
        String s = querySendDetails(bizId.toString());
        return code;
    }

    /**
     * 查询发送详情
     */
    private String querySendDetails(String bizId) throws ClientException {
        CommonRequest request = new CommonRequest();
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("QuerySendDetails");
        // 接收短信的手机号码
        request.putQueryParameter("PhoneNumber", "156xxxxxxxx");
        // 短信发送日期，支持查询最近30天的记录。格式为yyyyMMdd，例如20191010。
        request.putQueryParameter("SendDate", "20191010");
        // 分页记录数量
        request.putQueryParameter("PageSize", "10");
        // 分页当前页码
        request.putQueryParameter("CurrentPage", "1");
        // 发送回执ID，即发送流水号。
        request.putQueryParameter("BizId", bizId);
        CommonResponse response = client.getCommonResponse(request);
        String data = response.getData();
        //log_print("querySendDetails", response.getData());
        return data;
    }

//    public static void main(String[] args) throws ClientException {
//        Message message = new Message();
//        String s = message.sendSms("16602705424");
//        System.out.println(s);
//    }



}
