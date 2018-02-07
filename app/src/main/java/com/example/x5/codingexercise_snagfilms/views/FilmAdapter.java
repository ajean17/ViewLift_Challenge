package com.example.x5.codingexercise_snagfilms.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.x5.codingexercise_snagfilms.R;
import com.example.x5.codingexercise_snagfilms.models.Film;

import java.util.ArrayList;
import java.util.List;

class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.ViewHolder> {
    private static final String TAG = FilmAdapter.class.getSimpleName() + "_TAG";
    List<Film> filmList = new ArrayList<>();

    public FilmAdapter() {
    }

    public FilmAdapter(List<Film> filmList) {
        this.filmList = filmList;
    }

    public void updateDataSet(List<Film> f) {
        this.filmList = f;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Film film = filmList.get(position);
        if(film != null && !film.getTitle().equals("") && !film.getTitle().isEmpty()) {
            holder.textView.setText(film.getTitle());
            Glide.with(holder.imageView.getContext()).load(film.getImages().getImage().get(0).getSrc()).into(holder.imageView);
        }
        else
            holder.textView.setText("No title");
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_film_image);
            textView = itemView.findViewById(R.id.item_film_title);
        }
    }
}
