package com.example.x5.codingexercise_snagfilms.views;

import android.util.Log;

import com.example.x5.codingexercise_snagfilms.data.RemoteDataSource;
import com.example.x5.codingexercise_snagfilms.models.Film;
import com.example.x5.codingexercise_snagfilms.models.FilmGroup;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FilmPresenter implements FilmContract.Presenter {

    FilmContract.View view;
    List<Film> filmList;
    RemoteDataSource remoteDataSource;

    FilmPresenter() {
    }

    public FilmPresenter(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void attachView(FilmContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void getFilms() {
        final Request request = new Request.Builder().url("http://www.snagfilms.com/apis/films.json?limit=10").build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();
                final FilmGroup filmGroup = gson.fromJson(json, FilmGroup.class);
                filmList = filmGroup.getFilms().getFilm();
                view.updateFilms(filmList);
            }
        });
    }
}
