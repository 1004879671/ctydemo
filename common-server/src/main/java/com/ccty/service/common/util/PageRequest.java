package com.ccty.service.common.util;

import com.ccty.service.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

@ApiModel("分页请求实体PageRequest")
public class PageRequest extends BaseEntity {

    /**
     * 当前页码
     */
    @ApiModelProperty(value = "当前页码")
    @DecimalMax(value = Integer.MAX_VALUE+"",message = "当前页码最大值")
    @DecimalMin(value = "0",message = "当前页码最少值")
    private Integer pageNum;
    /**
     * 每页数量
     */
    @ApiModelProperty(value = "每页数量")
    @DecimalMax(value = Integer.MAX_VALUE+"",message = "每页数量最大值")
    @DecimalMin(value = "0",message = "每页数量最少值")
    private int pageSize;

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public int getPageNum() {
        return pageNum;
    }
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
