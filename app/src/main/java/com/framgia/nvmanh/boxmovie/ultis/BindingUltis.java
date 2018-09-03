package com.framgia.nvmanh.boxmovie.ultis;

import android.databinding.BindingAdapter;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.framgia.nvmanh.boxmovie.screen.base.EndLessRecyclerOnScrollListener;

public class BindingUltis {
    @BindingAdapter({"imageUrl"})
    public static void loadImg(ImageView imageView, String url){
        Glide.with(imageView.getContext())
                .load(StringUltis.getImageUrl(url))
                .into(imageView);
    }

    @BindingAdapter({"setAdapter"})
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter){
        recyclerView.setAdapter(adapter);
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
}
