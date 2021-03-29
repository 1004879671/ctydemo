package com.ccty.service.common.entity;

import cn.hutool.core.date.DateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class User {

    @JsonProperty(value = "编号", defaultValue = "1")
    private int id;

    @JsonProperty(value = "姓名", defaultValue = "张三")
    private String name;

    @JsonProperty(value = "手机号", defaultValue = "152XXXX3345")
    private String phone;

    @JsonProperty(value = "年龄", defaultValue = "18")
    private int age;

    @JsonProperty(value = "创建时间", defaultValue = "2019/10/01")
    private Date createTime;


}