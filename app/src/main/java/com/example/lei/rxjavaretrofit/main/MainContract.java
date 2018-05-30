package com.example.lei.rxjavaretrofit.main;

import com.example.lei.rxjavaretrofit.BasePresenter;
import com.example.lei.rxjavaretrofit.BaseView;

public class MainContract {

    interface View extends BaseView{
        void showResult(String result);
    }

    interface Presenter extends BasePresenter{
        void request();
    }
}
