package com.framgia.nvmanh.boxmovie.screen.overview;

import android.databinding.ObservableField;

import com.framgia.nvmanh.boxmovie.data.model.Company;

public class ItemCompanyViewModel {
    public ObservableField<Company> observableCompany = new ObservableField<>();

    public void setCompany(Company company){
        observableCompany.set(company);
    }
}
