package com.lgm.store.controller;

import com.lgm.store.entitly.TProduct;
import com.lgm.store.service.TProductService;
import com.lgm.store.util.JsonResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("product")
public class ProductController extends BaseController {
    @Resource
    private TProductService productService;

    @RequestMapping("hotList")
    public JsonResult<List<TProduct>> findHotList() {
        List<TProduct> hotList = productService.findHotList();
        return new JsonResult<>(Ok, hotList);
    }

    @RequestMapping("{id}/details")
    public JsonResult<TProduct> findById(@PathVariable Integer id) {
        TProduct product = productService.findProductById(id);
        return new JsonResult<>(Ok, product);
    }
}
