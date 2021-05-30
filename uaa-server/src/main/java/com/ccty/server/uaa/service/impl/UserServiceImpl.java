package com.ccty.server.uaa.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccty.server.uaa.entity.TUser;
import com.ccty.server.uaa.mapper.UserMapper;
import com.ccty.server.uaa.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author nieqiuqiu
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, TUser> implements IUserService {

}
