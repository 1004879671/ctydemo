package com.ccty.service.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@TableName("Excel")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class Excel extends BaseEntity{
    
    @TableId(value = "id" , type= IdType.ASSIGN_UUID)
    private String id;

    @TableField(value="name")
    private String name;

    @TableField(value="age")
    private Integer age;

    @TableField(value="height")
    private Double height;

    @TableField(value="weight")
    private Double weight;

    @TableField(value="edu")
    private String edu;

    @TableField(value="create_Time")
    private Date createTime;

    @TableField(value="update_Time")
    private Date updateTime;

    @TableField(value="status")
    private Integer status;

}
