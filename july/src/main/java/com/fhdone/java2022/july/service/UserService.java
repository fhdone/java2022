package com.fhdone.java2022.july.service;

import com.fhdone.java2022.april.dto.test.User;

import java.util.List;

public interface UserService {
    
    List<User> selectUser(User user);
    
}
