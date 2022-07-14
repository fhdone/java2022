package com.fhdone.java2022.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ContactMapper {

    @Select("SELECT * FROM CONTACTS")
    List<Map<String, Object>> findContact( );
 
    
    
}
