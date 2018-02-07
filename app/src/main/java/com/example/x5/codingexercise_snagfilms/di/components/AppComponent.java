package com.example.x5.codingexercise_snagfilms.di.components;

import com.example.x5.codingexercise_snagfilms.di.modules.AppModule;
import com.example.x5.codingexercise_snagfilms.di.modules.FilmListModule;
import com.example.x5.codingexercise_snagfilms.di.scope.AppScope;

import dagger.Component;

@AppScope
@Component(modules = AppModule.class)
public interface AppComponent {
    FilmListComponent plus (FilmListModule filmListModule);
}
