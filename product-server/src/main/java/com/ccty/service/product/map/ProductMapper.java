package com.ccty.service.product.map;

import com.ccty.service.product.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 接口继承
 */
@Mapper
public interface ProductMapper {

     Product findById(@Param("id") String id);

     void save(Product product);

     void update(Product product);

     void delete(@Param("id")String id);

}
