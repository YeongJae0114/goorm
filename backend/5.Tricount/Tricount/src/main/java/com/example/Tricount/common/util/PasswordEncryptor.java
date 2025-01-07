package com.example.Tricount.common.util;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component
public class PasswordEncryptor {
    public static String encryptPassword(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes()); // MD5 해싱
    }
    // 비밀번호 검증
    public static boolean matches(String rawPassword, String encryptedPassword) {
        // 입력된 비밀번호를 암호화하여 기존 암호화된 비밀번호와 비교
        return encryptPassword(rawPassword).equals(encryptedPassword);
    }
}
