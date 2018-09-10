package com.framgia.nvmanh.boxmovie.screen.overview;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.nvmanh.boxmovie.R;
import com.framgia.nvmanh.boxmovie.data.model.Company;
import com.framgia.nvmanh.boxmovie.databinding.ItemCompanyBinding;

import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {

    private Context mContext;
    private List<Company> mCompanies;

    public CompanyAdapter(Context context, List<Company> companies) {
        mContext = context;
        mCompanies = companies;
    }

    @NonNull
    @Override
    public CompanyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCompanyBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_company, parent,
                false);
        return new ViewHolder(binding, mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyAdapter.ViewHolder holder, int position) {
        holder.bindView(mCompanies.get(position));
    }

    @Override
    public int getItemCount() {
        return mCompanies != null ? mCompanies.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemCompanyViewModel mViewModel;
        public ViewHolder(ItemCompanyBinding binding, Context context) {
            super(binding.getRoot());
            mViewModel = new ItemCompanyViewModel(context);
            binding.setViewModel(mViewModel);
        }

        private void bindView(Company company){
            mViewModel.setCompany(company);
        }
    }
}
