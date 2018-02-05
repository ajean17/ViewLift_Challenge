package com.example.x5.codingexercise_snagfilms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.x5.codingexercise_snagfilms.models.Film;
import com.example.x5.codingexercise_snagfilms.models.FilmGroup;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    private OkHttpClient client;
    private List<Film> filmList;
    private RecyclerView recyclerView;
    private FilmAdapter filmAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        readInFIlms();
        setUpRecyclerView();
    }

    private void initViews() {
        client = new OkHttpClient();
        recyclerView = (RecyclerView) findViewById(R.id.rv_films);
        filmList = new ArrayList<>();
    }

    private void readInFIlms() {
        final Request request = new Request.Builder().url("http://www.snagfilms.com/apis/films.json?limit=10").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();
                final FilmGroup filmGroup = gson.fromJson(json, FilmGroup.class);
                Thread thread = new Thread() {
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(filmGroup != null) {
                                    Log.d(TAG, "run: Got the group");
                                    filmList = filmGroup.getFilms().getFilm();
                                    if(filmList != null && !filmList.isEmpty()) {
                                        Log.d(TAG, "run: Got the film list, size is: " + filmList.size());
                                        filmAdapter.updateDataSet(filmList);
                                    }
                                    else
                                        Log.d(TAG, "run: No film list");
                                }
                                else
                                    Log.d(TAG, "run: No group found");
                            }
                        });
                    }
                };
                thread.start();

            }
        });
    }

    private void setUpRecyclerView() {
        filmAdapter = new FilmAdapter(filmList);
        recyclerView.setAdapter(filmAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    }
}
