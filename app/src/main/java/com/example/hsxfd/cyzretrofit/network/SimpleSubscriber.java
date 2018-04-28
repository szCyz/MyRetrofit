package com.example.hsxfd.cyzretrofit.network;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;


import org.reactivestreams.Subscriber;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class SimpleSubscriber<T> implements Observer<T> {

    private Activity activity;
    private String rand;
    private Context context;
    public SimpleSubscriber(Activity activity) {
        Log.e("OkHttp","SimpleSubscriber");
        this.activity = activity;
    }

    public SimpleSubscriber(Context context) {
        this.context = context;
    }
    @Override
    public void onNext(T t) {
        Log.e("OkHttp","onNext");
        if(t instanceof WrapperRspEntity){
            Log.e("OkHttp","onNext"+((WrapperRspEntity) t).getCount());
            if (((WrapperRspEntity) t).getCount()==20) {
                call((T) ((WrapperRspEntity) t).getBooks());
            }else{
                onError(new TimeoutException());
            }
        }else{
            Log.e("OkHttp","onNext null");
        }


    }

    @Override
    public void onError(Throwable e) {
        String errormsg;
        Log.e("OkHttp","onError:"+e.toString());
        e.printStackTrace();
        onComplete();
    }

    @Override
    public void onComplete() {
        Log.e("OkHttp","onComplete");
    }


    @Override
    public void onSubscribe(Disposable d) {

    }

    public abstract void call(T t);

}
