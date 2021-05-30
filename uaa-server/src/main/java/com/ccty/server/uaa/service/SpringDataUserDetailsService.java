package com.ccty.server.uaa.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccty.server.uaa.entity.Permission;
import com.ccty.server.uaa.entity.TUser;
import com.ccty.server.uaa.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 **/
@Service
public class SpringDataUserDetailsService implements UserDetailsService {

    @Autowired
    IUserService userService;

    @Autowired
    UserMapper userMapper;

    //根据 账号查询用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //将来连接数据库根据账号查询用户信息
        QueryWrapper<TUser> wrapper = new QueryWrapper();
        wrapper.eq("username",username);
        TUser user = userService.getOne(wrapper);
        if(user == null){
            //如果用户查不到，返回null，由provider来抛出异常
            return null;
        }
        //根据用户的id查询用户的权限
        List<String> permissions = new ArrayList<>();
        List<Permission> permissionList = userMapper.findPermissionsByUserId(user.getId());
        if(permissionList != null){
            permissionList.forEach(x ->{
                permissions.add(x.getCode());
            });
        }

        //将permissions转成数组
        String[] permissionArray = new String[permissions.size()];
        permissions.toArray(permissionArray);
        //将userDto转成json
        String principal = JSON.toJSONString(user);
        UserDetails userDetails = User
                .withUsername(principal).password(user.getPassword()).authorities(permissionArray).build();
        return userDetails;

    }
}
