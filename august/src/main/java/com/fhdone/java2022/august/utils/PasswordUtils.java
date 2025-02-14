package com.fhdone.java2022.august.utils;

import java.security.SecureRandom;

public class PasswordUtils {

    private static final String CHAR_STRING =
            "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz123456789@";

    public static final int PAWSSWORD_LENGTH  = 10;
    
    
    /**
     * 生成随机密码
     *
     * @param length 密码长度
     * @return 随机生成的密码
     */
    public static String generatePassword(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            // 生成随机索引
            int randomIndex = random.nextInt(CHAR_STRING.length());
            // 从CHAR_STRING中取出字符，并添加到StringBuilder中
            sb.append(CHAR_STRING.charAt(randomIndex));
        }
        return sb.toString();
    }
    
    public static String generatePassword() {
        return generatePassword(PAWSSWORD_LENGTH);
    }
    
}
