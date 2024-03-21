package com.sipc.intelligentfarmbackend.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sipc.intelligentfarmbackend.utils.RedisUtil;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
@ServerEndpoint("/websocket")
public class Websocket {
    private Session session;
    private static ConcurrentHashMap<String, Websocket> webSocketMap = new ConcurrentHashMap<>();
    @OnOpen
    public void onOpen(Session session) {
        log.info("websocket已连接");
        this.session = session;
        RedisUtil.set("websocket_status","1");
        RedisUtil.expire("websocket_status",2000000000);
        webSocketMap.put("1",this);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Message received from " + session.getId() + ": " + message);
        // 可以在这里处理收到的消息，并发送响应给客户端
        // session.getBasicRemote().sendText("Response to message: " + message);
    }

    @OnClose
    public void onClose(Session session) {
        log.info("websocket已断开");
        webSocketMap.remove("1");
        RedisUtil.set("websocket_status","0");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
    }

    public void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            log.error("sendMessage()序列化异常");
        }
        try {
            this.session.getBasicRemote().sendText(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Websocket getWebsocket(String key){
        return webSocketMap.get(key);
    }
}
