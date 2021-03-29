package com.ccty.service.product.map;

import com.ccty.service.product.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findUserById(Integer id);
}
