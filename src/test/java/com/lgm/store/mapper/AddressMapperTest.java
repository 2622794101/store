package com.lgm.store.mapper;

import com.lgm.store.entitly.Address;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressMapperTest {
    @Resource
    private AddressMapper addressMapper;

    @Test
    void selectAddressByUid() {
        List<Address> addresses = addressMapper.selectAddressByUid(8);
        System.out.println(addresses);
    }

}