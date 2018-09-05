package com.framgia.nvmanh.boxmovie.screen.detail;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.nvmanh.boxmovie.R;
import com.framgia.nvmanh.boxmovie.data.model.Video;
import com.framgia.nvmanh.boxmovie.databinding.ItemVideoBinding;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>{

    private Context mContext;
    private List<Video> mVideos;

    public VideoAdapter(Context context, List<Video> videos) {
        mContext = context;
        mVideos = videos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemVideoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(
                parent.getContext()),
                R.layout.item_video, parent,
                false);
        return new ViewHolder(mContext, binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(mVideos.get(position));
    }

    @Override
    public int getItemCount() {
        return mVideos != null ? mVideos.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemVideoBinding mBinding;
        private ItemVideoViewModel mViewModel;
        public ViewHolder(Context context, ItemVideoBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = new ItemVideoViewModel(context);
            mBinding.setViewModel(mViewModel);
        }

        private void bindView(Video video){
            mViewModel.setVideo(video);
        }
    }
}
