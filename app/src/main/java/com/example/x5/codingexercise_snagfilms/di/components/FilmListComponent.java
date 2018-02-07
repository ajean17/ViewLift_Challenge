package com.example.x5.codingexercise_snagfilms.di.components;


import com.example.x5.codingexercise_snagfilms.di.modules.FilmListModule;
import com.example.x5.codingexercise_snagfilms.di.scope.ActivityScope;
import com.example.x5.codingexercise_snagfilms.views.FilmActivity;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = FilmListModule.class)
public interface FilmListComponent {
    void inject(FilmActivity filmActivity);
}
