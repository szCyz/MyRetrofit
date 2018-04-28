package com.example.hsxfd.cyzretrofit.presenter;

/**
 * 基本的Presenter，在需MVP模式中负责View与Model的交互
 * @param <V>
 * @param <M>
 */
public class Presenter<V extends View, M extends Model> {
    //持有M层和V层，必须是要实现BaseView和BaseModel
    public V mView;
    public M mModel;

    public void setPresenter(V view, M model) {
        mView = view;
        mModel = model;
    }

    public void setPresenter(V view) {
        mView = view;
    }

}
