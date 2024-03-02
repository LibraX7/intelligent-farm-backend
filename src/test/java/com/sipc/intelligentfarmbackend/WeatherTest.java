//package com.sipc.intelligentfarmbackend;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import com.sipc.intelligentfarmbackend.pojo.model.request.WeatherRequest;
//
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//
//public class WeatherTest {
//    public static void main(String[] args) {
//            // 替换为实际的API URL
//            String apiUrl = "http://api.yytianqi.com/forecast7d?city=CH010100&key=1wdpff40ltc8nj8b";
//
//            try {
//                // 创建HttpClient
//                HttpClient httpClient = HttpClient.newHttpClient();
//
//                // 创建GET请求
//                HttpRequest request = HttpRequest.newBuilder()
//                        .uri(URI.create(apiUrl))
//                        .build();
//
//                // 发送GET请求并接收响应
//                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//
//                // 检查响应状态码
//                if (response.statusCode() == 200) {
//                    // 使用Jackson库将JSON数据映射到实体类
//                    ObjectMapper objectMapper = new ObjectMapper();
//                    objectMapper.registerModule(new JavaTimeModule());
//                    WeatherRequest entity = objectMapper.readValue(response.body(), WeatherRequest.class);
//
//                } else {
//                    System.err.println("HTTP request failed with status code: " + response.statusCode());
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//    }
//}
//
//
//
//
//
//
//
