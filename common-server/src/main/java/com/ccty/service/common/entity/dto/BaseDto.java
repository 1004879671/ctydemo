package com.ccty.service.common.entity.dto;

import com.ccty.service.common.entity.BaseEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class BaseDto<T extends BaseEntity> {

    public T toEntity(Class<T> tClass){
        T t =BeanUtils.instantiateClass(tClass);
        BeanUtils.copyProperties(this,t);
        covert(t);
        return t;
    }

    /**
     * 用户dto转换po时，手动设置需要赋值的属性
     * @param t
     */
    protected   void covert(T t){}
}
