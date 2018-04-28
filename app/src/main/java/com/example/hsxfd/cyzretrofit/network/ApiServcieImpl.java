package com.example.hsxfd.cyzretrofit.network;

import android.content.Context;
import android.util.Log;

public class ApiServcieImpl {
    private static Context mContext;

    private ApiServcieImpl() {

    }
    public static ApiService getInstance(Context context) {
        mContext = context;
        Log.e("OkHttp","ApiService getInstance");
        return RetrofitManager.getInstance(mContext)
                .createReq(ApiService.class);
    }

    /**
     * Retrofit生成接口对象.
     */
    private static class createAPIService {
        //Retrofit会根据传入的接口类.生成实例对象.
        private static final ApiService apiService = RetrofitManager.getInstance(mContext)
                .createReq(ApiService.class);
    }
}
