package com.fhdone.java2022.august.utils;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.fhdone.java2022.august.utils.NumberUtils.safeGetDecimal;

public class NumberUtilsTest {


    @Test
    public void testSafeGetDecimal_ValidDecimalString() {
        Map<String, String> r = new HashMap<>();
        r.put("field1", "123.45");

        BigDecimal result = safeGetDecimal(r, "field1");
        Assertions.assertEquals(new BigDecimal("123.45"), result);
    }

    @Test
    public void testSafeGetDecimal_ValidNegativeDecimalString() {
        Map<String, String> r = new HashMap<>();
        r.put("field1", "-123.45");
        BigDecimal result = safeGetDecimal(r, "field1");
        Assertions.assertEquals(new BigDecimal("-123.45"), result);
    }

    @Test
    public void testSafeGetDecimal_NullValue() {
        Map<String, String> r = new HashMap<>();
        r.put("field1", null);
        BigDecimal result = safeGetDecimal(r, "field1");
        Assertions.assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void testSafeGetDecimal_FieldNotPresent() {
        Map<String, String> r = new HashMap<>();
        BigDecimal result = safeGetDecimal(r, "field1");
        Assertions.assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void testSafeGetDecimal_EmptyString() {
        Map<String, String> r = new HashMap<>();
        r.put("field1", "");
        BigDecimal result = safeGetDecimal(r, "field1");
        Assertions.assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void testSafeGetDecimal_InvalidDecimalString() {
        Map<String, String> r = new HashMap<>();
        r.put("field1", "invalid");
        BigDecimal result = safeGetDecimal(r, "field1");
        Assertions.assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void testSafeGetDecimal_Scalability() {
        Map<String, String> r = new HashMap<>();
        for (int i = 0; i < 1000000; i++) {
            r.put("field" + i, String.valueOf(i));
        }

        BigDecimal result = safeGetDecimal(r, "field999999");
        Assertions.assertEquals(new BigDecimal("999999"), result);
    }



}