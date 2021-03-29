package com.ccty.service.product.log.service.impl;

import com.ccty.service.product.log.entity.ExceptionInfo;
import com.ccty.service.product.log.mapper.ExceptionInfoMapper;
import com.ccty.service.product.log.service.IExceptionInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志异常信息表 服务实现类
 * </p>
 *
 * @author caitianyu
 * @since 2021-03-29
 */
@Service
public class ExceptionInfoServiceImpl extends ServiceImpl<ExceptionInfoMapper, ExceptionInfo> implements IExceptionInfoService {

}
