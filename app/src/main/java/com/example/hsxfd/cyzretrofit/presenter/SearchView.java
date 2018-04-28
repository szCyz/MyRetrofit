package com.example.hsxfd.cyzretrofit.presenter;


/**
 * 自定义MVP模式的搜索view，之所以用mvp模式是为了方便处理耦合
 */
public interface SearchView<T> extends View{
    void searchSuccess(T t);
    void searchFail(String errorMsg);
}
