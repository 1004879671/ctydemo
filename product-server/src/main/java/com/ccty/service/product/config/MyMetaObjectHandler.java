package com.ccty.service.product.config;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class MyMetaObjectHandler  implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.fillStrategy(metaObject,"createdBy","admin");
        this.fillStrategy(metaObject, "createdTime", DateUtil.date()); // 也可以使用(3.3.0 该方法有bug)
        this.fillStrategy(metaObject,"updateBy","admin");
        this.fillStrategy(metaObject, "updateTime", DateUtil.date()); // 也可以使用(3.3.0 该方法有bug)
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateBy","admin", metaObject);
        this.fillStrategy(metaObject, "updateTime", DateUtil.date()); // 也可以使用(3.3.0 该方法有bug)
    }
}
