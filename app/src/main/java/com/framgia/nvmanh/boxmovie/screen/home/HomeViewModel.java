package com.framgia.nvmanh.boxmovie.screen.home;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.framgia.nvmanh.boxmovie.screen.popular.PopularActivity;

public class HomeViewModel {
    private HomeFragment mHomeFragment;

    public HomeViewModel(HomeFragment homeFragment){
        mHomeFragment = homeFragment;
    }
}
