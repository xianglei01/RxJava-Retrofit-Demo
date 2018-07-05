package com.example.data.request;

import android.net.ParseException;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * @author xianglei
 * @created 2018/7/5 10:51
 */
public abstract class RequestCallBack<T> implements Callback<T> {

    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void onResponse(final Call<T> call, final Response<T> response) {
        if (response.isSuccessful()) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    onSuccess(call, response);
                }
            });
        }
    }

    @Override
    public void onFailure(Call<T> call, final Throwable t) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Throwable throwable = t;
                //获取最根源的异常
                while (throwable.getCause() != null) {
                    throwable = throwable.getCause();
                }
                onFailure(handlerException(throwable));
            }
        });
    }

    private ApiException handlerException(Throwable t) {
        ApiException ex;
        if (t instanceof HttpException) {
            //HTTP错误
            HttpException httpException = (HttpException) t;
            ex = new ApiException(t, httpException.code());
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                    //权限错误，需要实现
                    break;
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    //均视为网络错误
                    ex.setDisplayMessage("网络错误");
                    break;
            }
        } else if (t instanceof ResultException) {
            //服务器返回的错误
            ResultException resultException = (ResultException) t;
            ex = new ApiException(resultException, resultException.getErrCode());
        } else if (t instanceof JsonParseException
                || t instanceof JSONException
                || t instanceof ParseException) {
            //均视为解析错误
            ex = new ApiException(t, ApiException.PARSE_ERROR);
            ex.setDisplayMessage("解析错误");
        } else {
            //未知错误
            ex = new ApiException(t, ApiException.UNKNOWN);
            ex.setDisplayMessage("未知错误");
        }
        return ex;
    }

    public abstract void onSuccess(Call<T> call, Response<T> response);

    public abstract void onFailure(ApiException e);
}
