package com.assignment.assignment.model.response;

import java.io.Serializable;


public class UIBean<T> implements Serializable {
    private T data;

    public UIBean(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public UIBean() {
    }
}