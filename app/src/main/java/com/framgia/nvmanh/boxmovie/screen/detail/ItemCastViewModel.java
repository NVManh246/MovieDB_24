package com.framgia.nvmanh.boxmovie.screen.detail;

import android.databinding.ObservableField;

import com.framgia.nvmanh.boxmovie.data.model.Cast;

public class ItemCastViewModel {
    public ObservableField<Cast> observableCast = new ObservableField<>();

    public void setCast(Cast cast){
        observableCast.set(cast);
    }
}
