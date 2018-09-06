package com.framgia.nvmanh.boxmovie.ultis;

import android.databinding.BindingAdapter;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.framgia.nvmanh.boxmovie.BuildConfig;
import com.framgia.nvmanh.boxmovie.screen.base.EndLessRecyclerOnScrollListener;
import com.framgia.nvmanh.boxmovie.screen.genres.GridSpacingItemDecoration;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class BindingUltis {
    @BindingAdapter({"imageUrl"})
    public static void loadImg(ImageView imageView, String url){
        Glide.with(imageView.getContext())
                .load(StringUltis.getImageUrl(url))
                .into(imageView);
    }

    @BindingAdapter({"imageUrlYoutube"})
    public static void loadImgYoutube(ImageView imageView, String key){
        Glide.with(imageView.getContext())
                .load(StringUltis.getImageUrlYoutube(key))
                .into(imageView);
    }

    @BindingAdapter({"setAdapter"})
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter){
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({"addDecoration"})
    public static void addDecoration(RecyclerView recyclerView,
                                     GridSpacingItemDecoration itemDecoration){
        recyclerView.addItemDecoration(itemDecoration);
    }

    @BindingAdapter({"addLinearDecoration"})
    public static void addLinearDecoration(RecyclerView recyclerView,
                                     RecyclerView.ItemDecoration itemDecoration){
        recyclerView.addItemDecoration(itemDecoration);
    }

    @BindingAdapter({"setOnScroll"})
    public static void setScroll(RecyclerView recyclerView,
                                 EndLessRecyclerOnScrollListener listener){
        recyclerView.addOnScrollListener(listener);
    }

    @BindingAdapter({"onNavigationChanged"})
    public static void setOnNavigationChanged(
            BottomNavigationView bottomNavigationView,
            BottomNavigationView.OnNavigationItemSelectedListener listener){
        bottomNavigationView.setOnNavigationItemSelectedListener(listener);
    }

    @BindingAdapter("setAdapterViewPager")
    public static void setAdapter(ViewPager viewPager, PagerAdapter adapter){
        viewPager.setAdapter(adapter);
    }

    @BindingAdapter({"autoSwipe"})
    public static void setAutoSwipe(ViewPager viewPager, int i){
        viewPager.setCurrentItem(i, true);
    }

    @BindingAdapter("onInitializedListener")
    public static void setOnInitializedListener(
            YouTubePlayerView youTubePlayerView, YouTubePlayer.OnInitializedListener listener){
        youTubePlayerView.initialize(BuildConfig.YOUTUBE_API_KEY, listener);
    }
}
