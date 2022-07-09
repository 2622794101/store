package com.lgm.store.mapper;

import com.lgm.store.entitly.CartVO;
import com.lgm.store.entitly.TCart;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author L
 * @description 针对表【t_cart】的数据库操作Mapper
 * @createDate 2022-07-08 18:23:05
 * @Entity com.lgm.store.entitly.TCart
 */
public interface TCartMapper {

    Integer insert(TCart cart);

    Integer updateCartCount(Integer cid
            , Integer num
            , @Param("modifiedUser") String modifiedUser
            , @Param("modifiedTime") Date modifiedTime);

    TCart findCartByUidAndPid(Integer uid, Integer pid);

    List<CartVO> findVoByUid(Integer uid);

    TCart selectByCid(Integer cid);

    List<CartVO> findVoByCids(Integer[] cids);
}
