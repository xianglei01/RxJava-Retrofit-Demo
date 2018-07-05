package com.example.domain.listener;

public interface CallBack<T> {

    void onSuccess(T data);
    void onError();
}
