package com.lgm.store.service.impl;

import com.lgm.store.entitly.CartVO;
import com.lgm.store.service.TCartService;
import com.lgm.store.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TCartServiceImplTest {
    @Resource
    private TCartService cartService;

    @Test
    void addToCart() {
        try {
            Integer uid = 2;
            Integer pid = 10000007;
            Integer amount = 1;
            String username = "Tom";
            cartService.addToCart(uid, pid, amount, username);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    void getVoByCids() {
        List<CartVO> voByCids = cartService.getVoByCids(8, new Integer[]{1, 2, 3, 4});
        voByCids.forEach(System.out::println);
    }
}