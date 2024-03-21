//package com.sipc.intelligentfarmbackend.config.test.subscriber;
//
//import cn.hutool.json.JSONObject;
//import cn.hutool.json.JSONUtil;
//import com.sipc.intelligentfarmbackend.pojo.dto.SensorData;
//import com.sipc.intelligentfarmbackend.pojo.model.para.CameraTo;
//import com.sipc.intelligentfarmbackend.utils.RedisUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.logging.log4j.util.Strings;
//import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.integration.annotation.ServiceActivator;
//import org.springframework.integration.channel.DirectChannel;
//import org.springframework.integration.config.EnableIntegration;
//import org.springframework.integration.core.MessageProducer;
//import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
//import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
//import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.MessageHandler;
//import org.springframework.messaging.MessagingException;
//
//
//@Configuration
//@EnableIntegration
//@Slf4j
//public class MqttConsumerConfig {
//    @Value("${spring.mqtt.test2.username}")
//    private String username;
//
//    @Value("${spring.mqtt.test2.password}")
//    private String password;
//
//    @Value("${spring.mqtt.test2.url}")
//    private String hostUrl;
//
//    @Value("${spring.mqtt.test2.clientId}")
//    private String clientId;
//
//    @Value("${spring.mqtt.test2.topic}")
//    private String defaultTopic;
//
//    @Value("${spring.mqtt.test2.timeout}")
//    private Integer timeout;
//
//    @Value("${spring.mqtt.test2.qos}")
//    private Integer qos;
//
//
////    private MqttClient client;
//
//    /**
//     * 在bean初始化后连接到服务器
//     */
//
//    @Bean
//    public MessageChannel mqttSensorPropertyInputChannel() {
//
//        return new DirectChannel();
//    }
//
//    @Bean(name = "SensorPropertyMessageProducer")
//    public MessageProducer inbound(){
//        DefaultMqttPahoClientFactory mqttPahoClientFactory = new DefaultMqttPahoClientFactory();
//
//        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
//
//        if (Strings.isEmpty(username) || Strings.isEmpty(password)) log.info("--------mqtt正在使用匿名连接--------");
//        else {
//            mqttConnectOptions.setUserName(username);
//            mqttConnectOptions.setPassword(password.toCharArray());
//            mqttConnectOptions.setConnectionTimeout(timeout);
//            mqttConnectOptions.setAutomaticReconnect(true);
//        }
//
//        mqttPahoClientFactory.setConnectionOptions(mqttConnectOptions);
//
//        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
//                hostUrl, clientId, mqttPahoClientFactory
//        );
//        adapter.setConverter(new DefaultPahoMessageConverter());
//        adapter.setQos(qos);
//        adapter.setOutputChannel(mqttSensorPropertyInputChannel());
//        adapter.addTopic(defaultTopic);
//
//        return adapter;
//    }
//
//    @Bean
//    @ServiceActivator(inputChannel = "mqttSensorPropertyInputChannel")
//    public MessageHandler PropertyMessageHandler() {
//        return new MessageHandler() {
//            @Override
//            public void handleMessage(@NotNull Message<?> message) throws MessagingException {
//
//                String topic = (String) message.getHeaders().get("mqtt_receivedTopic");
//                assert topic != null;
//                JSONObject param = JSONUtil.parseObj(message.getPayload()).getJSONObject("param");
//
//                CameraTo cameraTo = new CameraTo();
//                if (param.containsKey("cameraRTMP"))
//                    cameraTo.setRtmp(param.getStr("cameraRTMP"));
//                if (param.containsKey("cameraHLS"))
//                    cameraTo.setHls(param.getStr("cameraHLS"));
//                if (param.containsKey("cameraRTMP ") || param.containsKey("cameraHLS")){
//                    RedisUtil.set("camera", JSONUtil.toJsonStr(cameraTo));
//                }
//                SensorData sensor = new SensorData();
//                sensor.setMessageType("plant-status");
//                sensor.setTemperature(param.getDouble("temperature"));
//                sensor.setMoisture(param.getDouble("humidity"));
//                sensor.setPh(param.getDouble("PH"));
//                sensor.setPhosphorus(param.getDouble("phosphorus"));
//                sensor.setNitrogen(param.getDouble("nitrogen"));
//                sensor.setPotassium(param.getDouble("potassium"));
//                RedisUtil.set("plant-status",JSONUtil.toJsonStr(sensor));
//                RedisUtil.expire("plant-status",2000000000);
//
//            }
//        };
//    }
//
//
////    public void connect(){
////        try {
////            //创建MQTT客户端对象
////            client = new MqttClient(hostUrl,clientId,new MemoryPersistence());
////            //连接设置
////            MqttConnectOptions options = new MqttConnectOptions();
////            //是否清空session，设置为false表示服务器会保留客户端的连接记录，客户端重连之后能获取到服务器在客户端断开连接期间推送的消息
////            //设置为true表示每次连接到服务端都是以新的身份
////            options.setCleanSession(true);
////            //设置连接用户名
////            if(StringUtils.isNotEmpty(username))
////                 options.setUserName(username);
////            //设置连接密码
////            if(StringUtils.isNotEmpty(password))
////                options.setPassword(password.toCharArray());
////            //设置超时时间，单位为秒
////            options.setConnectionTimeout(timeout);
////            //设置心跳时间 单位为秒，表示服务器每隔1.5*20秒的时间向客户端发送心跳判断客户端是否在线
////            options.setKeepAliveInterval(20);
////            //设置遗嘱消息的话题，若客户端和服务器之间的连接意外断开，服务器将发布客户端的遗嘱信息
////            options.setWill("willTopic",(clientId + "与服务器断开连接").getBytes(),0,false);
////            //设置回调
////            client.setCallback(new MqttConsumerCallBack());
////            client.connect(options);
////            //订阅主题
////            //消息等级，和主题数组一一对应，服务端将按照指定等级给订阅了主题的客户端推送消息
////            int[] qos = {1,1};
////            //主题
////            String[] topics = {"obj/We6c4mTE5TDev1ce/#","obj/We6c4mTE5TDev1ce/#"};
////            //订阅主题
////            client.subscribe(topics,qos);
////        } catch (MqttException e) {
////            e.printStackTrace();
////        }
////    }
//
//
//}
//
