package com.example.x5.codingexercise_snagfilms.di.modules;

import android.content.Context;

import com.example.x5.codingexercise_snagfilms.data.RemoteDataSource;
import com.example.x5.codingexercise_snagfilms.di.scope.AppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    String BASE_URL;
    Context context;

    public AppModule(String BASE_URL, Context context) {
        this.BASE_URL = BASE_URL;
        this.context = context;
    }

    //    Pass the dependencies that are used in sub-components on the this components.
    @AppScope
    @Provides
    RemoteDataSource provideRemoteDataSource(){
        return new RemoteDataSource(BASE_URL, context);
    }

    @AppScope
    @Provides
    Context providesContext(){
        return this.context;
    }
}
