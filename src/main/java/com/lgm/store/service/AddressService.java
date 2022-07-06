package com.lgm.store.service;

import com.lgm.store.entitly.Address;

/**
 * @author L
 * @description 针对表【t_address】的数据库操作Service
 * @createDate 2022-07-05 13:11:41
 */
public interface AddressService {
    void addAddress(Integer uid, String modifiedUser, Address address);
}
