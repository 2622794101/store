package com.lgm.store.util;

import lombok.AllArgsConstructor;

import java.io.Serializable;

public class JsonResult<E> implements Serializable {
    //状态码
    private Integer state;
    //描述信息
    private String message;
    //返回的数据
    private E data;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public JsonResult(Integer state, String message, E data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public JsonResult(Integer state, E data) {
        this.state = state;
        this.data = data;
    }

    public JsonResult(String message, E data) {
        this.message = message;
        this.data = data;
    }

    public JsonResult(Integer state, String message) {
        this.state = state;
        this.message = message;
    }

    public JsonResult(E data) {
        this.data = data;
    }

    public JsonResult(String message) {
        this.message = message;
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult() {
    }
}
