//package com.sipc.intelligentfarmbackend.config.test.subscriber;
//
//import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
//import org.eclipse.paho.client.mqttv3.MqttCallback;
//import org.eclipse.paho.client.mqttv3.MqttMessage;
//
//public class MqttConsumerCallBack implements MqttCallback{
//
//    /**
//     * 客户端断开连接的回调
//     */
//    @Override
//    public void connectionLost(Throwable throwable) {
//        System.out.println("与服务器断开连接，可重连");
//    }
//    /**
//     * 消息到达的回调
//     */
//    @Override
//    public void messageArrived(String topic, MqttMessage message) throws Exception {
//
//    }
//
//    /**
//     * 消息发布成功的回调
//     */
//    @Override
//    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
//
//    }
//
//}
//
