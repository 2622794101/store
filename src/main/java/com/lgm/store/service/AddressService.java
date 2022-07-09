package com.lgm.store.service;

import com.lgm.store.entitly.Address;

import java.util.List;

/**
 * @author L
 * @description 针对表【t_address】的数据库操作Service
 * @createDate 2022-07-05 13:11:41
 */
public interface AddressService {
    void addAddress(Integer uid, String modifiedUser, Address address);
    List<Address> showAddressByUid(Integer uid);
    Address showAddressByAid(Integer aid);
}
