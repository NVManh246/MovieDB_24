package com.framgia.nvmanh.boxmovie.ultis;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.framgia.nvmanh.boxmovie.screen.base.EndLessRecyclerOnScrollListener;

public class BindingUltis {
    @BindingAdapter({"setAdapter"})
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter){
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter({"setOnScroll"})
    public static void setScroll(RecyclerView recyclerView,
                                 EndLessRecyclerOnScrollListener listener){
        recyclerView.addOnScrollListener(listener);
    }
}
