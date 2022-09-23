package com.fhdone.java2022.august.service.impl;

import com.fhdone.java2022.march.dto.test.User;
import com.fhdone.java2022.august.mapper.test.UserMapper;
import com.fhdone.java2022.august.service.UserService;
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
