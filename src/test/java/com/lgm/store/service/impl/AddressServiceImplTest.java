package com.lgm.store.service.impl;

import com.lgm.store.entitly.Address;
import com.lgm.store.service.AddressService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressServiceImplTest {

    @Resource
    private AddressService addressService;

    @Test
    void testAddAddress() {
        Address address = new Address();
        address.setName("张三");
        address.setAddress("重庆xxxxxxxxxxx");
        address.setUid(8);
        addressService.addAddress(8, "admin", address);

    }

    @Test
    void showAddressByUid() {
        System.out.println(addressService.showAddressByUid(8));
    }

    @Test
    void showAddressByAid() {
        System.out.println(addressService.showAddressByAid(2));
    }
}