package com.example.hsxfd.cyzretrofit.presenter;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;

import com.example.hsxfd.cyzretrofit.model.WeatherModel;
import com.example.hsxfd.cyzretrofit.network.ApiServcieImpl;
import com.example.hsxfd.cyzretrofit.network.ModelFilteredFactory;
import com.example.hsxfd.cyzretrofit.network.SimpleSubscriber;

import java.util.HashMap;
import java.util.List;

/**
 * 搜索Presenter
 * @param <T> 回调回去的数据类型
 * @param <M> 搜索结果的模型
 */
public class SearchPresenter<T,M extends Model> extends Presenter<SearchView<T>,M>{
    /**
     * @param type 搜索类型
     * @param keyword 搜索关键字
     */
    public void search(Activity activity,int type, String keyword){
        Log.e("SearchPresenter","search:"+keyword);
        if(!TextUtils.isEmpty(keyword)){
            HashMap<String,Object> params = new HashMap<>();
            params.put("location",keyword);

            ModelFilteredFactory.compose(ApiServcieImpl.getInstance(activity).searchWeather(params))
                    .subscribe(new SimpleSubscriber<List<WeatherModel>>(activity) {
                        @Override
                        public void call(List<WeatherModel> weatherModels) {
                            //返回给V层
                            Log.e("SearchPresenter","call:"+weatherModels.toString());
                            mView.searchSuccess((T) weatherModels);
                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError(e);
                            mView.searchFail(e.toString());
                        }
                    });
        }
    }
}
