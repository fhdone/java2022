package com.fhdone.java2022.july.mapper.test;

import com.fhdone.java2022.march.dto.test.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> selectUser(User user);

}
