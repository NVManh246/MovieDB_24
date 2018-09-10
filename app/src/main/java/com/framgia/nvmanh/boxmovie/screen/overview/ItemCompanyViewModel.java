package com.framgia.nvmanh.boxmovie.screen.overview;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;

import com.framgia.nvmanh.boxmovie.data.model.Company;
import com.framgia.nvmanh.boxmovie.screen.movie.MovieActivity;

public class ItemCompanyViewModel {
    public ObservableField<Company> observableCompany = new ObservableField<>();

    private Context mContext;

    public ItemCompanyViewModel(Context context) {
        mContext = context;
    }

    public void setCompany(Company company){
        observableCompany.set(company);
    }

    public void onClickItemCompany(){
        Company company = observableCompany.get();
        Intent intent = MovieActivity.getMovieIntent(mContext,
                MovieActivity.SEARCH_BY_COMPANY, company.getID(), company.getName());
        mContext.startActivity(intent);
    }
}
