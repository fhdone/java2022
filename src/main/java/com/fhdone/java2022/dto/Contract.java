package com.fhdone.java2022.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("Contract")
public class Contract {
    
    Long ID;
    String EMAIL;
    String FIRSTNAME;
    String LASTNAME;
    String TELEPHONE;

}
