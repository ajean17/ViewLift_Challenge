
package com.example.x5.codingexercise_snagfilms.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FilmGroup {

    @SerializedName("films")
    @Expose
    private Films films;

    public Films getFilms() {
        return films;
    }

    public void setFilms(Films films) {
        this.films = films;
    }

}
