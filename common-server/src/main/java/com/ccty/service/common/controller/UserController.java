package com.ccty.service.common.controller;

import com.ccty.service.common.common.CommonResponse;
import com.ccty.service.common.entity.SysUser;
import com.ccty.service.common.service.SysUserService;
import com.ccty.service.common.util.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags="用户信息查询相关接口")
@RestController
@Validated
@RequestMapping("/user")
public class UserController {
    @Autowired
    SysUserService sysUserService;


    @PostMapping("/getUserInfo")
    @ApiOperation(value="分页查询用户信息")
    public CommonResponse getUserInfo(@ApiParam(value = "分页信息") @RequestBody @Valid  PageRequest pageRequest){

//        pageRequest.setPageNum(2);
//        pageRequest.setPageSize(2);
//        return new CommonResponse().toPage(sysUserService.getPageInfo(pageRequest));
        return new CommonResponse().success(null);
    }

    @PostMapping("/findUsers")
    @ApiOperation(value="查询用户信息")
    public CommonResponse findUsers(@ApiParam(value = "用户请求实体") @RequestBody SysUser sysUser){

//        sysUser.setId("00002");

        return new CommonResponse().success(sysUserService.findUsers(sysUser));
    }

    @GetMapping("/findUserById")
    @ApiOperation(value="根据id查询用户信息")
    public CommonResponse qryUserById(){

        return new CommonResponse().success(sysUserService.findByUserId("00001"));
    }


    @PostMapping("/saveSysUser")
    @ApiOperation(value="保存用户信息")
    public CommonResponse saveSysUser(@ApiParam(value = "用户请求实体") @RequestBody SysUser sysUser){

        return new CommonResponse().success(sysUserService.saveSysUser(sysUser));
    }


}
