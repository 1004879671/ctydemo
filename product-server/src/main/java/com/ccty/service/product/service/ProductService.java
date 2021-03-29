package com.ccty.service.product.service;

import com.ccty.service.product.entity.Product;

public interface ProductService {

    void relStock();

    /**
     * 根据id查询
     */
    Product findById(String  id);

    /**
     * 保存
     */
    void save(Product product);
    /**
     * 更新
     */
    void update(Product product);
    /**
     * 删除
     */
    void delete(String id);
}
