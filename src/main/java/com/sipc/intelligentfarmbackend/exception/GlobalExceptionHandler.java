package com.sipc.intelligentfarmbackend.exception;


import com.sipc.intelligentfarmbackend.pojo.model.res.CommonResult;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@ControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<CommonResult<Object>> handleSaberBaseException(BaseException e) {
        return new ResponseEntity<>(new CommonResult<>(e.getCode().getCode(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    public void sendLarkNotification(String webhookUrl, String title, String messageBody) {
        HttpClient client = HttpClient.newHttpClient();

        String json = String.format("""
                {
                    "msg_type": "post",
                    "content": {
                        "post": {
                            "zh_cn": {
                                "title": "%s",
                                "content": [
                                    [
                                        {
                                            "tag": "at",
                                            "user_id": "all"
                                        }
                                    ],
                                    [
                                        {
                                            "tag": "text",
                                            "text": "%s"
                                        }
                                    ]
                                ]
                            }
                        }
                    }
                }
                """, title, messageBody);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(webhookUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response status code: " + response.statusCode());
            System.out.println("Response body: " + response.body());
        } catch (Exception e) {
            throw new BaseException("exception handle failed", e);
        }
    }
}
