package com.lgm.store.service;

import com.lgm.store.entitly.CartVO;

import java.util.List;

/**
 * @author L
 * @description 针对表【t_cart】的数据库操作Service
 * @createDate 2022-07-08 18:23:05
 */
public interface TCartService {
    void addToCart(Integer uid, Integer pid, Integer amount, String modifiedUser);

    List<CartVO> getCartVoByUid(Integer uid);

    /**
     * return 返回购物车添加成功后的数量
     */
    Integer addNum(Integer cid, Integer uid, String modifiedUser);

    Integer subNum(Integer cid, Integer uid, String modifiedUser);

    List<CartVO> getVoByCids(Integer uid,Integer[] cid);
}
