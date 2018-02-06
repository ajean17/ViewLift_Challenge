package com.example.x5.codingexercise_snagfilms.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.x5.codingexercise_snagfilms.R;
import com.example.x5.codingexercise_snagfilms.models.Film;
import com.example.x5.codingexercise_snagfilms.models.FilmGroup;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements FilmContract.View{

    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    //private OkHttpClient client;
    private List<Film> filmList = new ArrayList<>();;
    private RecyclerView recyclerView;
    private FilmAdapter filmAdapter;

    private FilmPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new FilmPresenter();
        presenter.attachView(this);
        setUpRecyclerView();
        presenter.getFilms();
    }

    private void setUpRecyclerView() {
        recyclerView = findViewById(R.id.rv_films);
        filmAdapter = new FilmAdapter(filmList);
        recyclerView.setAdapter(filmAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateFilms(List<Film> newFilmList) {
        filmList.clear();
        filmList = newFilmList;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                filmAdapter.updateDataSet(filmList);
            }
        });
    }
}
