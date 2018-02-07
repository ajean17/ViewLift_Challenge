package com.example.x5.codingexercise_snagfilms.data;

import android.content.Context;

import javax.inject.Inject;

public class RemoteDataSource {
    Context context;

    private String BASE_URL;

    @Inject
    public RemoteDataSource(String BASE_URL, Context context) {
        this.BASE_URL = BASE_URL;
        this.context = context;
    }
}
