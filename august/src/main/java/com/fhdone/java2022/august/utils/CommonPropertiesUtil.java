package com.fhdone.java2022.august.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CommonPropertiesUtil {

    public static Map<String, Object> updateCommonProperties( String userId) {
        return commonProperties(false, userId);
    }

    public static Map<String, Object> insertCommonProperties(boolean isInsert, String userId) {
        return commonProperties(true, userId);
    }


    public static Map<String, Object> commonProperties(boolean isInsert, String userId) {

        Map<String, Object> properties = new HashMap<>();
        Date date =  new Date();
        if (isInsert) {
            properties.put("CREATEDATE", date);
            properties.put("CREATEUSER", userId);
            properties.put("UPDATEDATE", date);
            properties.put("UPDATEUSER", userId);
        } else {
            properties.put("UPDATEDATE", date);
            properties.put("UPDATEUSER", userId);
        }
        return properties;
    }

    public static void commonProperties(Map bo, boolean isInsert, String userId) {

        Date date =  new Date();
        if (isInsert) {
            bo.put("CREATEDATE", date);
            bo.put("CREATEUSER", userId);
            bo.put("UPDATEDATE", date);
            bo.put("UPDATEUSER", userId);
        } else {
            bo.put("UPDATEDATE", date);
            bo.put("UPDATEUSER", userId);
        }
    }


}
