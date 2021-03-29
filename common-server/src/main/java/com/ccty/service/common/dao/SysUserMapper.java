package com.ccty.service.common.dao;

import com.ccty.service.common.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserMapper {

    /**
     * 查询全部用户
     * @return
     */
    List<SysUser> selectAll();

    /**
     * 分页查询用户
     * @return
     */
    List<SysUser> selectPage();

    SysUser findUserById(@Param("id")String id);

    List<SysUser> findUsers(SysUser sysUser);

    int saveSysUser(SysUser sysUser);
}
