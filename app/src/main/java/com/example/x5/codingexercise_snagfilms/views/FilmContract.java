package com.example.x5.codingexercise_snagfilms.views;

import com.example.x5.codingexercise_snagfilms.models.Film;
import com.example.x5.codingexercise_snagfilms.utils.BasePresenter;
import com.example.x5.codingexercise_snagfilms.utils.BaseView;

import java.util.List;

public interface FilmContract {

    interface View extends BaseView {
        void updateFilms(List<Film> recipeList);
    }

    interface Presenter extends BasePresenter<View> {
        void getFilms();
    }
}
