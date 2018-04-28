package com.example.hsxfd.cyzretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.hsxfd.cyzretrofit.network.ApiServcieImpl;
import com.example.hsxfd.cyzretrofit.network.ModelFilteredFactory;
import com.example.hsxfd.cyzretrofit.network.SimpleSubscriber;

import org.reactivestreams.Subscription;

import java.util.HashMap;
import java.util.List;


public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);
        HashMap<String,Object> params = new HashMap<>();
        ModelFilteredFactory.compose(ApiServcieImpl.getInstance(this).searchBook("A"))
                .subscribe(new SimpleSubscriber<List<BookModel>>(this) {
                    @Override
                    public void call(List<BookModel> bookModels) {
                        Log.e("OkHttp","call"+bookModels.get(0).toString());
                    }
                });

    }
}
