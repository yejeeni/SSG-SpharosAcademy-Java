package com.ssg.customlogindb.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 비밀번호를 생성해주는 객체
 */
@Slf4j
@Component
public class PasswordCreater {

    public String getCryptPassword(String pwd){
        /**
         * salt를 적용하여 비밀번호를 암호화시켜주는 객체
         * 내부적으로 salt를 사용하므로 같은 문자열이어도 생성할 때마다 암호화 결과물은 변경
         */
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        return bCryptPasswordEncoder.encode(pwd);
    }
}
