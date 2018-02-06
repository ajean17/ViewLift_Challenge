package com.example.x5.codingexercise_snagfilms.utils;

public interface BasePresenter<V extends BaseView> {
    void attachView(V view);
    void detachView();
}
