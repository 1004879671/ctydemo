package com.ccty.server.uaa.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author nieqiuqiu
 */
@Data
@TableName(value="t_user")
public class TUser {
    @TableId
    private Long id;
    @TableField(value="username")
    private String username;
    private String password;
    private String fullname;
    private String mobile;

}
