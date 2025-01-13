package com.fhdone.java2022.august.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;


@Slf4j
public class PasswordUtilsTest {

    @Test
    public void generatePassword() {
        
        for(int i=0; i<10000; i++){
            String pwd = PasswordUtils.generatePassword();
            log.info(pwd);
            Assert.assertEquals(  10, pwd.length());
        }
    }
}