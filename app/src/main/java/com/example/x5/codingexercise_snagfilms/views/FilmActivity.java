package com.example.x5.codingexercise_snagfilms.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.x5.codingexercise_snagfilms.R;
import com.example.x5.codingexercise_snagfilms.models.Film;

import java.util.List;

public class FilmActivity extends AppCompatActivity implements FilmContract.View{

    private static final String TAG = FilmActivity.class.getSimpleName() + "_TAG";
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
        filmAdapter = new FilmAdapter();
        recyclerView = findViewById(R.id.rv_films);
        recyclerView.setAdapter(filmAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateFilms(final List<Film> newFilmList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                filmAdapter.updateDataSet(newFilmList);
            }
        });
    }
}
