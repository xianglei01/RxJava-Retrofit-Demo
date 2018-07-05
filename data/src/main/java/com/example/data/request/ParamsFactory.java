package com.example.data.request;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author xianglei
 * @created 2018/7/5 13:38
 */
public class ParamsFactory {

    public static MultipartBody.Part getMultipatBody(String key, File file){
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        /**
         * 创建多部分拿上面的请求体做参数
         */
        return MultipartBody.Part.createFormData(key, file.getName(), requestFile);
    }
}
