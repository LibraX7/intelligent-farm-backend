//package com.sipc.intelligentfarmbackend.controller.test;
//
//
//import com.sipc.intelligentfarmbackend.aop.Pass;
//import com.sipc.intelligentfarmbackend.config.test.subscriber.MqttConsumerConfig;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//
//@Controller
//public class TestController {
//    @Autowired
//    private MqttConsumerConfig client;
//
//    @Value("${spring.mqtt.test2.clientId}")
//    private String clientId;
//
//    @RequestMapping("/connect")
//    @ResponseBody
//    @Pass
//    public String connect(){
//        client.connect();
//        return clientId + "连接到服务器";
//    }
//
//    @RequestMapping("/disConnect")
//    @ResponseBody
//    @Pass
//    public String disConnect(){
//        client.disConnect();
//        return clientId + "与服务器断开连接";
//    }
//}
//
