package com.fhdone.java2022.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("Contract")
public class Contract {
    
    Long id;
    String email;
    String firstname;
    String lastname;
    String telephone;

}
