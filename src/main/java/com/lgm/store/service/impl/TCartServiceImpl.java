package com.lgm.store.service.impl;

import com.lgm.store.entitly.CartVO;
import com.lgm.store.entitly.TCart;
import com.lgm.store.entitly.TProduct;
import com.lgm.store.service.TCartService;
import com.lgm.store.mapper.TCartMapper;
import com.lgm.store.service.TProductService;
import com.lgm.store.service.ex.InsertException;
import com.lgm.store.service.ex.cart.CartNotFoundException;
import com.lgm.store.service.ex.user.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author L
 * @description 针对表【t_cart】的数据库操作Service实现
 * @createDate 2022-07-08 18:23:05
 */
@Service
public class TCartServiceImpl implements TCartService {
    @Resource
    private TCartMapper cartMapper;
    @Resource
    private TProductService productService;

    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String modifiedUser) {
        TCart result = cartMapper.findCartByUidAndPid(uid, pid);
        if (result == null) {
            TCart cart = new TCart();
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            // 调用productService.findById(pid)查询商品数据，得到商品价格
            TProduct product = productService.findProductById(pid);
            cart.setPrice(product.getPrice());
            Date date = new Date();

            cart.setCreatedTime(date);
            cart.setCreatedUser(modifiedUser);
            cart.setModifiedTime(date);
            cart.setModifiedUser(modifiedUser);

            Integer insert = cartMapper.insert(cart);
            if (insert != 1) {
                throw new InsertException("加入购物车时出现未知异常");
            }
        } else {
            Integer cid = result.getCid();
            Integer count = result.getNum() + amount;
            Integer rows = cartMapper.updateCartCount(cid, count, modifiedUser, new Date());
            if (rows != 1) {
                throw new InsertException("添加购物车出现未知异常");
            }
        }

    }

    @Override
    public List<CartVO> getCartVoByUid(Integer uid) {
        List<CartVO> voByUid = cartMapper.findVoByUid(uid);
        return voByUid;
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String modifiedUser) {
        TCart cart = cartMapper.selectByCid(cid);
        if (cart == null) {
            throw new CartNotFoundException("购物车未找到");
        }
        Integer cartUid = cart.getUid();
        if (!cartUid.equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }

        Integer num = cart.getNum() + 1;

        Integer rows = cartMapper.updateCartCount(cid, num, modifiedUser, new Date());

        if (rows != 1) {
            throw new InsertException("修改商品数量时出现未知错误，请联系系统管理员");
        }
        return num;
    }

    @Override
    public Integer subNum(Integer cid, Integer uid, String modifiedUser) {
        TCart cart = cartMapper.selectByCid(cid);
        if (cart == null) {
            throw new CartNotFoundException("购物车未找到");
        }
        Integer cartUid = cart.getUid();
        if (!cartUid.equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }

        Integer num = cart.getNum() - 1;
        if (num == 0) {
            return 1;
        }

        Integer rows = cartMapper.updateCartCount(cid, num, modifiedUser, new Date());

        if (rows != 1) {
            throw new InsertException("修改商品数量时出现未知错误，请联系系统管理员");
        }
        return num;
    }

    @Override
    public List<CartVO> getVoByCids(Integer uid, Integer[] cid) {
        List<CartVO> list = cartMapper.findVoByCids(cid);

        Iterator<CartVO> iterator = list.iterator();
        while (iterator.hasNext()) {
            CartVO cartVO = iterator.next();
            if (!cartVO.getUid().equals(uid)) {
                iterator.remove();
            }
        }

        return list;
    }
}
