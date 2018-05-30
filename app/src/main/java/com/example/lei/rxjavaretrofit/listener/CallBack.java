package com.example.lei.rxjavaretrofit.listener;

public interface CallBack<T> {

    void onSuccess(T data);
    void onError();
}
