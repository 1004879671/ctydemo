package com.ccty.server.uaa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccty.server.uaa.entity.Permission;
import com.ccty.server.uaa.entity.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author caitianyu
 */
@Mapper
public interface UserMapper extends BaseMapper<TUser> {

    List<Permission> findPermissionsByUserId(@Param("id") Long id);

}
