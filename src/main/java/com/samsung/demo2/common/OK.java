package com.samsung.demo2.common;

public class OK<T> extends Response {
    public T Data;

    public OK(int Status, String Message, T data)
    {
        super(Status, Message);
        this.Data = data;
    }
}
