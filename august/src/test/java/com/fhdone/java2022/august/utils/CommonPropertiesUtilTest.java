package com.fhdone.java2022.august.utils;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class CommonPropertiesUtilTest {

    @Test
    public void testUpdateCommonProperties() {
        String userId = "testUser";
        Map<String, Object> properties = CommonPropertiesUtil.updateCommonProperties(userId);
        
        assertNotNull(properties);
        assertEquals(userId, properties.get("UPDATEUSER"));
        assertNotNull(properties.get("UPDATEDATE"));
        assertNull(properties.get("CREATEDATE"));
        assertNull(properties.get("CREATEUSER"));
    }

    @Test
    public void testInsertCommonProperties() {
        String userId = "testUser";
        Map<String, Object> properties = CommonPropertiesUtil.insertCommonProperties(true, userId);
        
        assertNotNull(properties);
        assertEquals(userId, properties.get("CREATEUSER"));
        assertNotNull(properties.get("CREATEDATE"));
        assertEquals(userId, properties.get("UPDATEUSER"));
        assertNotNull(properties.get("UPDATEDATE"));
    }

    @Test
    public void testCommonPropertiesInsert() {
        String userId = "testUser";
        Map<String, Object> properties = CommonPropertiesUtil.commonProperties(true, userId);
        
        assertNotNull(properties);
        assertEquals(userId, properties.get("CREATEUSER"));
        assertNotNull(properties.get("CREATEDATE"));
        assertEquals(userId, properties.get("UPDATEUSER"));
        assertNotNull(properties.get("UPDATEDATE"));
    }

    @Test
    public void testCommonPropertiesUpdate() {
        String userId = "testUser";
        Map<String, Object> properties = CommonPropertiesUtil.commonProperties(false, userId);
        
        assertNotNull(properties);
        assertEquals(userId, properties.get("UPDATEUSER"));
        assertNotNull(properties.get("UPDATEDATE"));
        assertNull(properties.get("CREATEDATE"));
        assertNull(properties.get("CREATEUSER"));
    }

    @Test
    public void testCommonPropertiesWithMapInsert() {
        Map<String, Object> bo = new HashMap<>();
        String userId = "testUser";
        CommonPropertiesUtil.commonProperties(bo, true, userId);
        
        assertNotNull(bo);
        assertEquals(userId, bo.get("CREATEUSER"));
        assertNotNull(bo.get("CREATEDATE"));
        assertEquals(userId, bo.get("UPDATEUSER"));
        assertNotNull(bo.get("UPDATEDATE"));
    }

    @Test
    public void testCommonPropertiesWithMapUpdate() {
        Map<String, Object> bo = new HashMap<>();
        String userId = "testUser";
        CommonPropertiesUtil.commonProperties(bo, false, userId);
        
        assertNotNull(bo);
        assertEquals(userId, bo.get("UPDATEUSER"));
        assertNotNull(bo.get("UPDATEDATE"));
        assertNull(bo.get("CREATEDATE"));
        assertNull(bo.get("CREATEUSER"));
    }
}