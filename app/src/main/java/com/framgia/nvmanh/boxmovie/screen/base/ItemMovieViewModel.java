package com.framgia.nvmanh.boxmovie.screen.base;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.framgia.nvmanh.boxmovie.data.model.Movie;
import com.framgia.nvmanh.boxmovie.ultis.Contants;
import com.framgia.nvmanh.boxmovie.ultis.StringUltis;

public class ItemMovieViewModel {
    public ObservableField<Movie> observableMovie = new ObservableField<>();

    public void setMovie(Movie movie){
        observableMovie.set(movie);
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImg(ImageView imageView, String url){
        Glide.with(imageView.getContext())
                .load(StringUltis.getImageUrl(Contants.Server.BASE_IMAGE_URL, url))
                .into(imageView);
    }
}
