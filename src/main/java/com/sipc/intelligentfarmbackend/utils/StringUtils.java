package com.sipc.intelligentfarmbackend.utils;

import java.util.Random;

public class StringUtils {
    public static Boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static Boolean isNotEmpty(String str) {
        return str != null && !str.isEmpty();
    }

    /**
     * 生成随机的大写字母和数字混合的验证码
     * @param len 需要生成的长度
     * @return {@link String}
     */
    public static String generateRandomCode(int len) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            boolean nextIsChar = random.nextInt(2) % 2 == 0 ;
            if (nextIsChar) {
                sb.append((char)(65+random.nextInt(26)));
            } else {
                sb.append((char)(random.nextInt(10)+'0'));
            }
        }
        return sb.toString();
    }
}
