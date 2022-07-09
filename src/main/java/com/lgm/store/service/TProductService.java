package com.lgm.store.service;

import com.lgm.store.entitly.TProduct;

import java.util.List;

/**
 * @author L
 * @description 针对表【t_product】的数据库操作Service
 * @createDate 2022-07-08 13:47:34
 */
public interface TProductService {
    List<TProduct> findHotList();
    TProduct findProductById(Integer id);
}
