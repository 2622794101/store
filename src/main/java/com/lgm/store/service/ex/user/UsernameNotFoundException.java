package com.lgm.store.service.ex.user;

import com.lgm.store.service.ex.ServiceException;

/**
 * 用户数据不存在异常
 */
public class UsernameNotFoundException extends ServiceException {
    public UsernameNotFoundException() {
        super();
    }

    public UsernameNotFoundException(String message) {
        super(message);
    }

    public UsernameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameNotFoundException(Throwable cause) {
        super(cause);
    }

    protected UsernameNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
