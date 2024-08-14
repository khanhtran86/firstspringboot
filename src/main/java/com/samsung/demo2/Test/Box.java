package com.samsung.demo2.Test;

public class Box<T> {
    T object;
    public void Add(T object)
    {
        this.object = object;
    }

    public T Get()
    {
        return object;
    }
}
