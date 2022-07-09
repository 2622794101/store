package com.lgm.store.mapper;

import com.lgm.store.entitly.Address;

import java.util.List;

/**
 * @author L
 * @description 针对表【t_address】的数据库操作Mapper
 * @createDate 2022-07-05 13:11:41
 * @Entity com.lgm.store.entitly.TAddress
 */
public interface AddressMapper {

    Integer selectCountByUid(Integer uid);

    int insert(Address address);

    List<Address> selectAddressByUid(Integer uid);

    Address selectAddressByAid(Integer aid);
}
