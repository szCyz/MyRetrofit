package com.example.hsxfd.cyzretrofit.network;

import android.app.Activity;
import android.util.Log;


import com.example.hsxfd.cyzretrofit.utils.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class SimpleSubscriber<T> implements Observer<T> {


    private Activity activity;

    /**
     * 传个activity进来是为了方便统一处理的时候需要用到activity，比如api要求重新登录，则可统一跳转到登录界面等
     * @param activity
     */
    public SimpleSubscriber(Activity activity) {
        this.activity = activity;
        //这里也可以统一show ProgressDialog
    }

    @Override
    public void onNext(T t) {
        //这里是接口访问正常的回调
        call(t);//将结果回调回去
    }

    @Override
    public void onError(Throwable e) {
        //统一的error处理
        if (e instanceof APIException) {
            apiStateError((APIException) e);//自定义api的错误处理
        } else if (e instanceof SocketTimeoutException) {
            errorEvent("连接超时");
        } else if (e instanceof ConnectException) {
            errorEvent("连接超时");
        } else if (e instanceof HttpException) {
            errorEvent("无法识别访问地址");
        } else if(e instanceof UnknownHostException){
            errorEvent("无法识别访问地址");
        }else{
            errorEvent("xxxx");
        }
        e.printStackTrace();
    }

    @Override
    public void onComplete() {
        //这里是接口完成后的回调
        //这里可以统一 dismiss ProgressDialog
    }


    @Override
    public void onSubscribe(Disposable d) {
    }

    public abstract void call(T t);

    //普通异常处理
    public void errorEvent(String errormsg) {
        if(activity!=null){
            ToastUtil.show(activity,errormsg);
        }else if(activity!=null){
            ToastUtil.show(activity,errormsg);
        }
    }

    //接口异常处理
    public void apiStateError(APIException e){
        switch (e.status){
            case "240"://"APP 服务被禁用"
                errorEvent(e.message);
                break;
            case "201"://"APP被用户自己禁用，请在控制台解禁"
                errorEvent(e.message);
                break;
        }
    }

}
