package com.ccty.service.product.api;

import com.ccty.service.product.entity.User;
import com.ccty.service.product.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserApi {
    @Autowired
    private UserService userService;

    @GetMapping("/findUser")
    public String index(Integer id ){
        User user = userService.findUserById(id);
        return user.getName();
    }

    @GetMapping("/test")
    public String test(Integer id ){
        return "这是测试";
    }
}
