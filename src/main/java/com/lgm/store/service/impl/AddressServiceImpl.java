package com.lgm.store.service.impl;

import com.lgm.store.entitly.Address;
import com.lgm.store.service.AddressService;
import com.lgm.store.mapper.AddressMapper;
import com.lgm.store.service.ex.InsertException;
import com.lgm.store.service.ex.address.AddressCountLimitException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author L
 * @description 针对表【t_address】的数据库操作Service实现
 * @createDate 2022-07-05 13:11:41
 */
@Service
public class AddressServiceImpl  implements AddressService{
    @Resource
    private AddressMapper addressMapper;

    @Value("${user.address.max-count}")
    private Integer AddressMaxCount;

    @Override
    public void addAddress(Integer uid, String modifiedUser, Address address) {
        Integer count = addressMapper.selectCountByUid(uid);
        if (count > AddressMaxCount) {
            throw new AddressCountLimitException("用户地址超过最大值");
        }
        address.setUid(uid);
        Integer isDelete = count == 0 ? 1 : 0;
        address.setIsDefault(isDelete);
        Date date = new Date();

        address.setModifiedUser(modifiedUser);
        address.setModifiedTime(date);
        address.setCreatedUser(modifiedUser);
        address.setCreatedTime(date);

        int insert = addressMapper.insert(address);
        if (insert != 1){
            throw new InsertException("插入收获数据时出现未知错误，请联系管理员解决！");
        }
    }
}
