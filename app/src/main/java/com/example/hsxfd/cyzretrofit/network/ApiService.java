package com.example.hsxfd.cyzretrofit.network;


import com.example.hsxfd.cyzretrofit.BookModel;

import java.util.HashMap;
import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("v2/book/search")
    Observable<WrapperRspEntity<List<BookModel>>> searchBook(@Query("q") String bookName);
}
