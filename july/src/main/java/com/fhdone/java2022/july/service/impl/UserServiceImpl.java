package com.fhdone.java2022.july.service.impl;

import com.fhdone.java2022.march.dto.test.User;
import com.fhdone.java2022.july.mapper.test.UserMapper;
import com.fhdone.java2022.july.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Override
    public List<User> selectUser(User user) {
        return userMapper.selectUser(user);
    }
}
