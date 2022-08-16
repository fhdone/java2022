package com.fhdone.java2022.july.mapper;

import com.fhdone.java2022.april.dto.Contract;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

    @Insert("INSERT INTO demo.CONTACTS (EMAIL, FIRSTNAME, LASTNAME, TELEPHONE)" +
        " VALUES( #{email} , #{firstname} , #{lastname}, #{telephone} ) " )
    long insertContract(Contract contract);

    
    List<Contract> selectContract(Contract contract);

    
}
