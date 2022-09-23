package com.fhdone.java2022.august.service;

import com.fhdone.java2022.march.dto.test.User;

import java.util.List;

public interface UserService {
    
    List<User> selectUser(User user);
    
}
