package com.fhdone.java2022.august.utils;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordUtilsTest {

    @Test
    public void generatePassword() {
        
        for(int i=0; i<10000; i++){
            String pwd = PasswordUtils.generatePassword();
            System.out.println(pwd);
            Assert.assertEquals(  10, pwd.length());
        }
    }
}