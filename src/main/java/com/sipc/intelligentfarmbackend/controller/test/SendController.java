//package com.sipc.intelligentfarmbackend.controller.test;
//
//
//import com.sipc.intelligentfarmbackend.aop.Pass;
//import com.sipc.intelligentfarmbackend.config.test.provider.MqttProviderConfiguration;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//public class SendController {
//    @Autowired
//    private MqttProviderConfiguration providerClient;
//
//    @RequestMapping("/sendMessage")
//    @ResponseBody
//    @Pass
//    public String sendMessage(int qos,boolean retained,String topic,String message){
//        try {
//            providerClient.publish(qos, retained, topic, message);
//            return "发送成功";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "发送失败";
//        }
//    }
//}
//
