package com.lgm.store.mapper;

import com.lgm.store.entitly.TProduct;

import java.util.List;

/**
 * @author L
 * @description 针对表【t_product】的数据库操作Mapper
 * @createDate 2022-07-08 13:47:34
 * @Entity com.lgm.store.entitly.TProduct
 */
public interface TProductMapper {
    List<TProduct> findHotList();
    TProduct findById(Integer id);


}
