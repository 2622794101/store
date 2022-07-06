package com.lgm.store.entitly;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component

public class User extends Base {
    private Integer uid;
    private String username;
    private String password;
    private String salt;
    private String phone;
    private String email;
    private Integer gender;
    private String avatar;
    private Integer isDelete;
}
