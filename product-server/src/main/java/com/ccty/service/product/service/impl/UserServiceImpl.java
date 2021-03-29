package com.ccty.service.product.service.impl;

import com.ccty.service.product.map.UserMapper;
import com.ccty.service.product.entity.User;
import com.ccty.service.product.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserById(Integer id){
        return userMapper.findUserById(id);
    }
}
