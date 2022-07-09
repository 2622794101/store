package com.lgm.store.controller;

import com.lgm.store.entitly.Address;
import com.lgm.store.service.AddressService;
import com.lgm.store.util.JsonResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.rmi.server.UID;
import java.util.List;

@RestController
@RequestMapping("address")
@CrossOrigin
public class AddressController extends BaseController {
    @Resource
    private AddressService addressService;

    @RequestMapping("addNewAddress")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String modifiedUser = getUsernameFromSession(session);
        addressService.addAddress(uid, modifiedUser, address);
        return new JsonResult<>(Ok);
    }

    @RequestMapping("showAddressByUid")
    public JsonResult<List<Address>> showAddressByUid(Integer uid) {
        List<Address> addresses = addressService.showAddressByUid(uid);
        return new JsonResult<>(Ok, addresses);
    }

    @RequestMapping("showAddressByAid")
    public JsonResult<Address> showAddressByAid(Integer aid) {
        Address address = addressService.showAddressByAid(aid);
        return new JsonResult<>(Ok, address);
    }

    @RequestMapping("showAddress")
    public JsonResult<List<Address>> showAddress(HttpSession session) {
        Integer uid = getUidFromSession(session);
        List<Address> addresses = addressService.showAddressByUid(uid);
        return new JsonResult<>(Ok, addresses);
    }


}
