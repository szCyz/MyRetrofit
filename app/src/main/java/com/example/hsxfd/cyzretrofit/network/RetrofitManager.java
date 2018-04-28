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
        return mRetrofit.create(reqServer);
    }

    //初始化Retrofit
    private void initRetrofit(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://api.map.baidu.com/") //设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();
    }

    /**
     * 自定义拦截器
     */
    public class RequestInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (request.method().equals("POST")) {

            } else if (request.method().equals("GET")) {
                request = addGetParams(chain);
            }
            return chain.proceed(request);
        }
    }

    /**
     * Get请求需添加的公共参数
     * @param chain
     * @return
     */
    private static Request addGetParams(Interceptor.Chain chain) {
        Request request;
        Request oldRequest = chain.request();
        // 添加公共参数
        HttpUrl authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .addQueryParameter("ak","6tYzTvGZSOpYB5Oc2YGGOKt8")
                .addQueryParameter("output", "json").build();

        request = oldRequest.newBuilder()
                .url(authorizedUrlBuilder)
                .build();
        return request;
    }



}
