package com.ccty.server.uaa.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value="t_permission")
public class Permission {
    @TableId
    private String id;
    //权限标识符
    private String code;
    private String description;
    private String url;
}
