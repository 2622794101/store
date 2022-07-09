package com.lgm.store.controller;

import com.lgm.store.service.ex.*;
import com.lgm.store.service.ex.address.AddressCountLimitException;
import com.lgm.store.service.ex.cart.CartNotFoundException;
import com.lgm.store.service.ex.file.FileEmptyException;
import com.lgm.store.service.ex.file.FileSizeException;
import com.lgm.store.service.ex.file.FileStateException;
import com.lgm.store.service.ex.file.FileTypeException;
import com.lgm.store.service.ex.product.ProductNotFoundException;
import com.lgm.store.service.ex.user.AccessDeniedException;
import com.lgm.store.service.ex.user.PasswordNotMatchException;
import com.lgm.store.service.ex.user.UsernameDuplicatedExeception;
import com.lgm.store.service.ex.user.UsernameNotFoundException;
import com.lgm.store.util.JsonResult;
import org.apache.tomcat.util.http.fileupload.impl.FileUploadIOException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

public class BaseController {
    /**
     * 操作成功返回参数
     * OK 状态200
     */
    public static final int Ok = 200;

    //请求处理方法
    //当前项目产生了异常，被同意拦截到此方法中，该方法处理异常
    @ExceptionHandler(ServiceException.class)
    public JsonResult<Throwable> HandlerException(Throwable e) {
        JsonResult<Throwable> throwableJsonResult = new JsonResult<>(e);
        if (e instanceof UsernameDuplicatedExeception) {
            throwableJsonResult.setState(4000);
            throwableJsonResult.setMessage("用户名已被占用");
        } else if (e instanceof UsernameNotFoundException) {
            throwableJsonResult.setState(4001);
            throwableJsonResult.setMessage("用户名未找到");
        } else if (e instanceof PasswordNotMatchException) {
            throwableJsonResult.setState(4002);
            throwableJsonResult.setMessage("用户名密码错误");
        } else if (e instanceof AddressCountLimitException) {
            throwableJsonResult.setState(4003);
            throwableJsonResult.setMessage("地址超过最大限制");
        } else if (e instanceof ProductNotFoundException) {
            throwableJsonResult.setState(4004);
            throwableJsonResult.setMessage("商品未找到");
        } else if (e instanceof AccessDeniedException) {
            throwableJsonResult.setState(4005);
            throwableJsonResult.setMessage("非法访问");
        } else if (e instanceof CartNotFoundException) {
            throwableJsonResult.setState(4007);
            throwableJsonResult.setMessage("购物车未找到");
        } else if (e instanceof InsertException) {
            throwableJsonResult.setState(5000);
            throwableJsonResult.setMessage("注册时产生了未知异常");
        } else if (e instanceof UpdateException) {
            throwableJsonResult.setState(5001);
            throwableJsonResult.setMessage("更新时时产生了未知异常");
        } else if (e instanceof FileEmptyException) {
            throwableJsonResult.setState(6001);
            throwableJsonResult.setMessage("空文件异常");
        } else if (e instanceof FileSizeException) {
            throwableJsonResult.setState(6002);
            throwableJsonResult.setMessage("文件大小异常");
        } else if (e instanceof FileStateException) {
            throwableJsonResult.setState(6003);
            throwableJsonResult.setMessage("文件状态异常");
        } else if (e instanceof FileTypeException) {
            throwableJsonResult.setState(6004);
            throwableJsonResult.setMessage("文件类型异常");
        } else if (e instanceof FileUploadIOException) {
            throwableJsonResult.setState(6005);
            throwableJsonResult.setMessage("文件IO异常");
        }
        return throwableJsonResult;
    }

    /**
     * 获取session对象中的uid
     *
     * @return
     */
    protected final Integer getUidFromSession(HttpSession session) {
        String uid = session.getAttribute("uid").toString();
        return Integer.valueOf(uid);
    }

    /**
     * 获取session对象中的username
     *
     * @param session 对象
     * @return
     */
    protected final String getUsernameFromSession(HttpSession session) {
        String username = session.getAttribute("username").toString();
        return username;
    }


}
