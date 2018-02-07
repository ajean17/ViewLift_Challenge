package com.example.x5.codingexercise_snagfilms.di.modules;

import com.example.x5.codingexercise_snagfilms.data.RemoteDataSource;
import com.example.x5.codingexercise_snagfilms.di.scope.ActivityScope;
import com.example.x5.codingexercise_snagfilms.views.FilmPresenter;

import dagger.Module;

@Module
public class FilmListModule {
    @ActivityScope
    FilmPresenter provideFilmPresenter(RemoteDataSource remoteDataSource){
        return new FilmPresenter(remoteDataSource);
    }
}
