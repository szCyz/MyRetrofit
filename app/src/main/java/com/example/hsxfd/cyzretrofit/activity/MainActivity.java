package com.example.hsxfd.cyzretrofit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.widget.TextView;

import com.example.hsxfd.cyzretrofit.R;
import com.example.hsxfd.cyzretrofit.model.WeatherModel;
import com.example.hsxfd.cyzretrofit.network.ApiServcieImpl;
import com.example.hsxfd.cyzretrofit.network.ModelFilteredFactory;
import com.example.hsxfd.cyzretrofit.network.SimpleSubscriber;
import com.example.hsxfd.cyzretrofit.presenter.SearchPresenter;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements com.example.hsxfd.cyzretrofit.presenter.SearchView<List<WeatherModel>>{

    private TextView text;
    private SearchView search;
    private SearchPresenter<List<WeatherModel>,WeatherModel> mSearchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        text = findViewById(R.id.text);
        search = findViewById(R.id.search);

        mSearchPresenter = new SearchPresenter<>();
        mSearchPresenter.setPresenter(this,new WeatherModel());
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mSearchPresenter.search(MainActivity.this,0,query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void searchSuccess(List<WeatherModel> weatherModels) {
        //搜索成功回调
        text.setText(weatherModels.toString());
    }

    @Override
    public void searchFail(String errorMsg) {
        //搜索失败回调
    }

    @Override
    public LifecycleTransformer bindLifecycle() {
        return null;
    }
}
