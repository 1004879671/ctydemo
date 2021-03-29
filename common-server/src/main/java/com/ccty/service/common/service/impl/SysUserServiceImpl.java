package com.ccty.service.common.service.impl;

import com.ccty.service.common.dao.SysUserMapper;
import com.ccty.service.common.entity.SysUser;
import com.ccty.service.common.service.SysUserService;
import com.ccty.service.common.util.PageRequest;
import com.ccty.service.common.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public SysUser findByUserId(String userId) {
        return sysUserMapper.findUserById(userId);
    }

    @Override
    public List<SysUser> findUsers(SysUser sysUser) {
        //模拟空指针异常   测试异常拦截器
//        Map m = new HashMap();
//        System.out.println(m.get("a").equals(""));

        return sysUserMapper.findUsers(sysUser);
    }

    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.selectAll();
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return null;
    }

//    @Override
//    public PageResult findPage(PageRequest pageRequest) {
//        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest));
//    }

    /**
     * 调用分页插件完成分页
     * @return
     */
//    @Override
//    public PageInfo<SysUser> getPageInfo(PageRequest pageRequest) {
//        int pageNum = pageRequest.getPageNum();
//        int pageSize = pageRequest.getPageSize();
//        PageHelper.startPage(pageNum, pageSize);
//
//        List<SysUser> sysMenus = sysUserMapper.selectPage();
//
//        return new PageInfo<SysUser>(sysMenus);
//    }

    @Override
    public int saveSysUser(SysUser sysUser) {
        return sysUserMapper.saveSysUser(sysUser);
    }
}
