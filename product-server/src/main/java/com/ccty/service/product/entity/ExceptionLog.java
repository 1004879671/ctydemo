package com.ccty.service.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@ToString
public class ExceptionLog extends BaseEntity{

    @TableField(value="exc_requ_raram")
    private String excRequParam;

    @TableField(value="oper_method")
    private String operMethod;

    @TableField(value="exc_name")
    private String excName;

    @TableField(value="exc_message")
    private String excMessage;

    @TableField(value="oper_url")
    private String operUrl;

    @TableField(value="oper_ip")
    private String operIp;

    @TableField(value="oper_ver")
    private String operVer;

}
