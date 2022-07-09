package com.lgm.store.service.impl;

import com.lgm.store.entitly.TProduct;
import com.lgm.store.service.TProductService;
import com.lgm.store.mapper.TProductMapper;
import com.lgm.store.service.ex.product.ProductNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author L
 * @description 针对表【t_product】的数据库操作Service实现
 * @createDate 2022-07-08 13:47:34
 */
@Service
public class TProductServiceImpl implements TProductService {

    @Resource
    private TProductMapper productMapper;

    @Override
    public List<TProduct> findHotList() {
        List<TProduct> hotList = productMapper.findHotList();
        return hotList;
    }

    @Override
    public TProduct findProductById(Integer id) {
        TProduct product = productMapper.findById(id);
        if (product == null) {
            throw new ProductNotFoundException("没有找到该商品");
        }

        // 将查询结果中的部分属性设置为null
        product.setPriority(null);
        product.setCreatedUser(null);
        product.setCreatedTime(null);
        product.setModifiedUser(null);
        product.setModifiedTime(null);

        return product;
    }

}
