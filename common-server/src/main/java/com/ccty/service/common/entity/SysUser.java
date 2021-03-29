package com.ccty.service.common.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sysuser")
@ApiModel
public class SysUser extends BaseEntity{
    @ApiModelProperty(value = "用户id")
    @Id
    @Column(name="id")
    private String id;

    @ApiModelProperty(value = "用户名")
    @Column(name="name")
    private String name;

    @ApiModelProperty(value = "年龄")
    @Column(name="age")
    private  String age;

    @ApiModelProperty(value = "性别")
    @Column(name="sex")
    private String sex;

    @ApiModelProperty(value = "出生日期")
    @Column(name="birth")
    private String birth;

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    @Override
    public String toString() {
        return "SysUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", birth='" + birth + '\'' +
                '}';
    }
}
