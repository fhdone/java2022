package com.fhdone.java2022.august.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.DecimalFormat;

@Slf4j
public class NumberTest {

    @Test
    public void getNumbericValue() {
        String numbericVal = "";
        try {
            Double doubleVal = Double.valueOf("1234567");
            numbericVal = DecimalFormat.getNumberInstance().format(doubleVal);
            log.info(numbericVal);
        } catch (NumberFormatException e) {
            log.error("千份位數字轉型錯誤");
        }
    }

    @Test
    public void getNumbericValue2() {
        String numbericVal = "";
        DecimalFormat df = new DecimalFormat("#,###");
        numbericVal = df.format(1234567);
        log.info(numbericVal);
    }



}
