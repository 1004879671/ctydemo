package com.ccty.service.common.entity.dto;

import com.ccty.service.common.entity.Excel;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@ApiModel
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ExcelDto extends  BaseDto<Excel>{
//    public ExcelDto(Excel excel){
//        BeanUtils.copyProperties(excel,this);
//    }

    @ApiModelProperty(value = "用户id")
    @Id
    private String id;

    @ApiModelProperty(value = "用户名")
    @JsonProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "年龄")
    @JsonProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "身高")
    @JsonProperty(value = "身高")
    private Double height;

    @ApiModelProperty(value = "体重")
    @JsonProperty(value = "体重")
    private Double weight;

    @ApiModelProperty(value = "受教育程度")
    @JsonProperty(value = "受教育程度")
    private String edu;


    @ApiModelProperty(value = "状态")
    @JsonProperty(value = "状态")
    private Integer status;


    @Override
    protected  void covert(Excel excel){
        excel.setId(id);
        excel.setName(name);
        excel.setAge(age);
        excel.setEdu(edu);
        excel.setHeight(height);
        excel.setWeight(weight);
        excel.setStatus(status);
    }
}
