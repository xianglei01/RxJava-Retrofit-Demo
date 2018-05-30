package com.example.lei.rxjavaretrofit.main;

import com.example.lei.rxjavaretrofit.listener.CallBack;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private MainResposity mResposity;
    public MainPresenter(MainContract.View view, MainResposity resposity){
        mView = view;
        mResposity = resposity;
    }

    @Override
    public void request() {
        if(mResposity != null){
            mResposity.search("123", new CallBack<String>() {
                @Override
                public void onSuccess(String data) {
                    if(mView != null){
                        mView.showResult(data);
                    }
                }

                @Override
                public void onError() {

                }
            });
        }
    }
}
