package com.example.data.request;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitUtil<T> {

    private static RetrofitUtil instance;
    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;
    private T mApiService;
    private Class<T> service;
    private String mUrl;

    public static RetrofitUtil getInstance(String url, Class service) {
        if (instance == null) {
            instance = new RetrofitUtil(url, service);
        }
        return instance;
    }

    private RetrofitUtil() {
    }

    private RetrofitUtil(String url, Class<T> service) {
        mUrl = url;
        SSLSocketFactoryUtil.MyX509TrustManager x509TrustManager = new SSLSocketFactoryUtil.MyX509TrustManager();
        getOkHttpClient();
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .hostnameVerifier(new SSLSocketFactoryUtil.TrustAllHostnameVerifier())
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.service = service;
        mApiService = mRetrofit.create(service);
        //        BlueService service = retrofit.create(BlueService.class);
//        service.profilePicture("https://s3.amazon.com/profile-picture/path");
    }

    public OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .build();
        }
        return mOkHttpClient;
    }

    public RetrofitUtil enableLeval(HttpLoggingInterceptor.Level leval) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(leval);
        mOkHttpClient = mOkHttpClient.newBuilder().addInterceptor(interceptor).build();
        return instance;
    }

    public RetrofitUtil addInterceptor(Interceptor interceptor) {
        mOkHttpClient = mOkHttpClient.newBuilder().addInterceptor(interceptor).build();
        return instance;
    }

    public RetrofitUtil sslSocketFactory(SSLSocketFactory sslSocketFactory, X509TrustManager x509TrustManager) {
        mOkHttpClient = mOkHttpClient.newBuilder().sslSocketFactory(sslSocketFactory, x509TrustManager).build();
        return instance;
    }

    public RetrofitUtil hostnameVerifier(HostnameVerifier hostnameVerifier) {
        mOkHttpClient = mOkHttpClient.newBuilder().hostnameVerifier(hostnameVerifier).build();
        return instance;
    }

    public RetrofitUtil create(){
        mRetrofit = mRetrofit.newBuilder().client(mOkHttpClient).build();
        mApiService = mRetrofit.create(service);
        return instance;
    }

    public T getApiService() {
        return mApiService;
    }

}
