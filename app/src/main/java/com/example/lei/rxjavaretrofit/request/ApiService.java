package com.example.lei.rxjavaretrofit.request;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("s")
    ApiObservable<String> search(@Query("ie") String type, @Query("wd") String keyWords);
}
