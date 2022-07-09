package com.lgm.store.service.impl;

import com.lgm.store.entitly.TProduct;
import com.lgm.store.service.TProductService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TProductServiceImplTest {

    @Resource
    private TProductService productService;

    @Test
    void findHotList() {
        List<TProduct> hotList =
                productService.findHotList();
        System.out.println(hotList);
    }

    @Test
    void findProductById() {
        System.out.println(productService.findProductById(10000006));
    }
}