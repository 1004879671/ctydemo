package com.ccty.service.common.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelVo {

    private String name;

    private Integer age;

    private Double height;

    private Double weight;

    private String edu;

    private Integer status;

    private Date updateTime;

    private Date createTime;



}
