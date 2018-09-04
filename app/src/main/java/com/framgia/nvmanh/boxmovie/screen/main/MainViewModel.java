package com.framgia.nvmanh.boxmovie.screen.main;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.framgia.nvmanh.boxmovie.R;
import com.framgia.nvmanh.boxmovie.screen.favorite.FavoriteFragment;
import com.framgia.nvmanh.boxmovie.screen.genres.GenresFragment;
import com.framgia.nvmanh.boxmovie.screen.home.HomeFragment;

public class MainViewModel {

    public ObservableField<BottomNavigationView.OnNavigationItemSelectedListener>
            mItemListener = new ObservableField<>();

    private FragmentManager mFragmentManager;
    private HomeFragment mHomeFragment;
    private GenresFragment mGenresFragment;
    private FavoriteFragment mFavoriteFragment;
    private Fragment mCurrentFragment;

    public MainViewModel(AppCompatActivity activity){
        mHomeFragment = HomeFragment.newInstance();
        mGenresFragment = GenresFragment.newInstance();
        mFavoriteFragment = FavoriteFragment.newInstance();
        mFragmentManager = activity.getSupportFragmentManager();
        initFragment();
        setChangedListener();
    }

    private void setChangedListener(){
        mItemListener.set(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_home:
                        showFragment(mHomeFragment, mCurrentFragment);
                        mCurrentFragment = mHomeFragment;
                        break;
                    case R.id.menu_category:
                        showFragment(mGenresFragment, mCurrentFragment);
                        mCurrentFragment = mGenresFragment;
                        break;
                    case R.id.menu_favorite:
                        showFragment(mFavoriteFragment, mCurrentFragment);
                        mCurrentFragment = mFavoriteFragment;
                        break;
                }
                return true;
            }
        });
    }

    private void initFragment(){
        mFragmentManager.beginTransaction()
                .add(R.id.frame_container, mHomeFragment)
                .add(R.id.frame_container, mGenresFragment)
                .add(R.id.frame_container, mFavoriteFragment)
                .commit();
        mFragmentManager.beginTransaction()
                .hide(mGenresFragment)
                .hide(mFavoriteFragment)
                .show(mHomeFragment)
                .commit();
        mCurrentFragment = mHomeFragment;
    }

    private void showFragment(Fragment fShow, Fragment fHide){
        mFragmentManager.beginTransaction().hide(fHide).show(fShow).commit();
    }
}
