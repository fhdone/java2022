package com.fhdone.java2022.august.utils;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;


public class NumberUtils {

    public static BigDecimal safeGetDecimal(Map<String, String> r, String field) {
        try {
            return Optional.ofNullable(r.get(field))
                    .map(BigDecimal::new)
                    .orElse(BigDecimal.ZERO);
        }catch (Exception e){
            return BigDecimal.ZERO;
        }
    }
    

}
