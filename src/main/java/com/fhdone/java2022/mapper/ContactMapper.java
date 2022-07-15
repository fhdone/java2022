package com.fhdone.java2022.mapper;

import com.fhdone.java2022.dto.Contract;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface ContactMapper {

    @Select("SELECT * FROM CONTACTS")
    List<Contract> queryContact( );

    @Select({"<script>",
        " SELECT * FROM CONTACTS where 1=1",
        "    <if test='id != null'>and id=#{id}</if>",
        "</script>"})
    List<Contract> queryContactByCondition( Map<String, Object>  paraMap);

    List<Contract> selectContract(Contract contract);

    
}
