package com.lgm.store.service.ex.user;

import com.lgm.store.service.ex.ServiceException;

public class UsernameDuplicatedExeception extends ServiceException {
    public UsernameDuplicatedExeception() {
        super();
    }

    public UsernameDuplicatedExeception(String message) {
        super(message);
    }

    public UsernameDuplicatedExeception(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameDuplicatedExeception(Throwable cause) {
        super(cause);
    }

    protected UsernameDuplicatedExeception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
