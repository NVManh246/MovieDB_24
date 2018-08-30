package com.framgia.nvmanh.boxmovie.ultis;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

public class BindingUltis {
    @BindingAdapter({"setAdapter"})
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter){
        recyclerView.setAdapter(adapter);
    }
}
