package com.example.hsxfd.cyzretrofit.network;


import android.util.Log;

import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ModelFilteredFactory {
    private final static ObservableTransformer transformer = new SimpleTransformer();
    /**
     * 将Observable<WrapperRspEntity<T>>转化Observable<T>,并处理WrapperRspEntity
     *
     * @return 返回过滤后的Observable.
     */
    @SuppressWarnings("unchecked")
    public static <T> Observable<T> compose(Observable<WrapperRspEntity<T>> observable){
        Log.e("OkHttp","compose");
        return observable.compose(transformer);
    }

    /**
     * 这个类的意义就是转换Observable.
     */
    private static class SimpleTransformer<T> implements ObservableTransformer<T, T> {
        //这里对Observable,进行一般的通用设置.不用每次用Observable都去设置线程以及重连设置
        @Override
        public ObservableSource<T> apply(Observable<T> observable) {
            return  observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io())
                    .flatMap((Function<? super T, ? extends ObservableSource<? extends T>>) new Function<WrapperRspEntity<T>, Observable<T>>() {
                        @Override
                        public Observable<T> apply(WrapperRspEntity<T> tWrapperRspEntity) throws Exception {
                            return flatResponse(tWrapperRspEntity);
                        }
                    });

        }
    }

    /**
     * 处理请求结果,BaseResponse
     * @param response 请求结果
     * @return 过滤处理, 返回只有data数据的Observable
     */
    private static Observable flatResponse(final WrapperRspEntity response){
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {
                Log.e("OkHttp",response.toString());

//                if (response.getCount()==20) {
//                    if (!e.isDisposed()) {
                        e.onNext(response);
//                    }
//                }else{
//                    if (!e.isDisposed()) {
//                        e.onError(new TimeoutException());
//                    }
//                }
            }
        });
    }
}
