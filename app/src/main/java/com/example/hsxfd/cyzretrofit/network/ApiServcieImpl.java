package com.example.hsxfd.cyzretrofit.network;

import android.content.Context;
import android.util.Log;

public class ApiServcieImpl {
    private static Context mContext;

    private ApiServcieImpl() {

    }
    public static ApiService getInstance(Context context) {
        mContext = context;
        return RetrofitManager.getInstance(mContext)
                .createReq(ApiService.class);
    }
}
