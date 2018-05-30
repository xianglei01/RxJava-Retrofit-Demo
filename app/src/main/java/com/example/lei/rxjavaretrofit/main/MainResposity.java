package com.example.lei.rxjavaretrofit.main;

import com.example.lei.rxjavaretrofit.listener.CallBack;
import com.example.lei.rxjavaretrofit.request.RetrofitUtil;

public class MainResposity {

    public void search(String keyWords, CallBack<String> callBack) {
        RetrofitUtil.getInstance()
                .getApiService()
                .search("UTF-8", keyWords)
                .excute(callBack);
    }
}
