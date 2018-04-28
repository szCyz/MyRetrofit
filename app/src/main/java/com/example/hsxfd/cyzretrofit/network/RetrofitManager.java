package com.example.hsxfd.cyzretrofit.network;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private Retrofit mRetrofit;
    private static Context mContext;
    private static RetrofitManager mRetrofitManager;
    private static OkHttpClient mOkHttpClient;

    private RetrofitManager(){
        initOkHttp();
        initRetrofit();
    }


    public static synchronized RetrofitManager getInstance(Context context){
        mContext = context;
        if (mRetrofitManager == null){
            Log.e("OkHttp","new RetrofitManager");
            mRetrofitManager = new RetrofitManager();
        }
        return mRetrofitManager;
    }


    //初始化OkHttp
    private void initOkHttp(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if(mOkHttpClient==null){
            synchronized (RetrofitManager.class) {
                if (mOkHttpClient == null) {
                    mOkHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(interceptor)
                            .addInterceptor(new RequestInterceptor())
                            .retryOnConnectionFailure(true)
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }

    public <T> T createReq(Class<T> reqServer){
        Log.e("OkHttp","createReq："+mRetrofit.baseUrl());
        return mRetrofit.create(reqServer);
    }

    //初始化Retrofit
    private void initRetrofit(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/") //设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();
        Log.v("OkHttp","initRetrofit");
    }

    private static class RequestInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Log.e("aaa",request.toString());
            if (request.method().equals("POST")) {
                if (request.body() instanceof FormBody) {
                    request = addPostFormParams(request);
                } else if (request.body() instanceof MultipartBody) {
                    request = addPostMultiParams(request);
                }
            } else if (request.method().equals("GET")) {
                request = addGetParams(chain);
                Log.e("aaa",request.toString());
            }
            return chain.proceed(request);
        }
    }
    //上传时
    private static Request addPostMultiParams(Request request) {
        // 添加公共参数
        MultipartBody.Builder builder = new MultipartBody.Builder().addFormDataPart("deviceId", "123456");
        MultipartBody oldBody = (MultipartBody) request.body();
        for (int i = 0; i < oldBody.size(); i++) {
            builder.addPart(oldBody.part(i));
        }
        oldBody = builder.build();
        request = request.newBuilder().post(oldBody).build();
        return request;
    }
    //正常时
    private static Request addPostFormParams(Request request) {
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        FormBody formBody = (FormBody) request.body();
        //把原来的参数添加到新的构造器，（因为没找到直接添加，所以就new新的）
        for (int i = 0; i < formBody.size(); i++) {
            bodyBuilder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
        }
        //添加公共参数
        formBody = bodyBuilder
                .addEncoded("deviceId", "123456").build();
        request = request.newBuilder().post(formBody).build();
        return request;
    }

    private static Request addGetParams(Interceptor.Chain chain) {
        Request request;
        Request oldRequest = chain.request();
        // 添加公共参数
        HttpUrl authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .addQueryParameter("deviceId", "123456").build();
        request = oldRequest.newBuilder()
                .url(authorizedUrlBuilder)
                .build();
        return request;
    }
}
