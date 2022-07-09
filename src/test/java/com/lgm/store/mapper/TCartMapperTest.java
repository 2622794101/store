package com.lgm.store.mapper;

import com.lgm.store.entitly.CartVO;
import com.lgm.store.entitly.TCart;
import org.apache.ibatis.annotations.Insert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TCartMapperTest {
    @Resource
    private TCartMapper tCartMapper;

    @Test
    void testInsert() {
        TCart cart = new TCart();
        cart.setUid(1);
        cart.setPid(2);
        cart.setNum(3);
        cart.setPrice(4L);
        Integer insert = tCartMapper.insert(cart);
        System.out.println(insert);
    }

    @Test
    void updateCartCount() {
        Integer admin = tCartMapper.updateCartCount(1, 5, "admin", new Date());
        System.out.println(admin);
    }

    @Test
    void findCartByUidAndPid() {
        TCart cartByUidAndPid = tCartMapper.findCartByUidAndPid(1, 2);
        System.out.println(cartByUidAndPid);
    }

    @Test
    void findVoByUid() {
        List<CartVO> voByUid =
                tCartMapper.findVoByUid(2);
        System.out.println(voByUid);
    }

    @Test
    void findVoByCids() {
        Integer[] ids = new Integer[]{1,2,3,4};
        List<CartVO> voByCids = tCartMapper.findVoByCids(ids);
        System.out.println(voByCids);
    }
}