package com.ccty.service.product.log.entity;

import com.ccty.service.product.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 日志异常信息表
 * </p>
 *
 * @author caitianyu
 * @since 2021-03-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ExceptionInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 请求参数
     */
    private String excRequParam;

    /**
     * 操作方法
     */
    private String operMethod;

    /**
     * 异常名称
     */
    private String excName;

    /**
     * 异常信息
     */
    private String excMessage;

    /**
     * 请求路径
     */
    private String operUrl;

    /**
     * 请求ip
     */
    private String operIp;

    /**
     * 操作版本
     */
    private String operVer;


}
