package com.lgm.store.controller;

import com.lgm.store.entitly.CartVO;
import com.lgm.store.service.TCartService;
import com.lgm.store.util.JsonResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("cart")
public class CartController extends BaseController {
    @Resource
    private TCartService cartService;

    @RequestMapping("addToCart")
    public JsonResult<Void> addToCart(Integer pid, Integer amount, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String modifiedUser = getUsernameFromSession(session);

        cartService.addToCart(uid, pid, amount, modifiedUser);
        return new JsonResult<>(Ok);
    }

    @RequestMapping("getCartList")
    public JsonResult<List<CartVO>> getCartList(HttpSession session) {
        Integer uid = getUidFromSession(session);
        List<CartVO> cartVoByUid = cartService.getCartVoByUid(uid);
        return new JsonResult<>(Ok, cartVoByUid);
    }

    @RequestMapping("addNum")
    public JsonResult<Integer> addNum(Integer cid, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String modifiedUser = getUsernameFromSession(session);
        Integer integer = cartService.addNum(cid, uid, modifiedUser);
        return new JsonResult<>(Ok, integer);
    }

    @RequestMapping("subNum")
    public JsonResult<Integer> subNum(Integer cid, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String modifiedUser = getUsernameFromSession(session);
        Integer integer = cartService.subNum(cid, uid, modifiedUser);
        return new JsonResult<>(Ok, integer);
    }

    @RequestMapping("sureList")
    public JsonResult<List<CartVO>> sureList(Integer[] cids, HttpSession session) {
        Integer uid = getUidFromSession(session);
        List<CartVO> voByCids = cartService.getVoByCids(uid, cids);
        return new JsonResult<>(Ok, voByCids);
    }
}
