package com.sipc.intelligentfarmbackend.timeTask;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.sipc.intelligentfarmbackend.pojo.dto.SensorData;
import com.sipc.intelligentfarmbackend.utils.RedisUtil;
import com.sipc.intelligentfarmbackend.websocket.Websocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessagePush {

    @Scheduled(cron = "10 * * * * *")
    public void pushData() {
        if(RedisUtil.get("websocket_status") != null && RedisUtil.get("websocket_status").equals("1")){
            Websocket websocket = Websocket.getWebsocket("1");
            JSONObject jsonObject = JSONUtil.parseObj(RedisUtil.get("plant-status"));
            SensorData fieldStatus = JSONUtil.toBean(jsonObject, SensorData.class);
            websocket.sendMessage(fieldStatus);
            log.info("" + fieldStatus);
        }
    }
}
