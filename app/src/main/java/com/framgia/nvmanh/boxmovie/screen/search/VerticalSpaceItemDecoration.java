package com.framgia.nvmanh.boxmovie.screen.search;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private final int verticalSpaceHeight;

    public VerticalSpaceItemDecoration(int verticalSpaceHeight) {
        this.verticalSpaceHeight = verticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if(position == 0){
            outRect.top = verticalSpaceHeight;
        }
        outRect.bottom = verticalSpaceHeight;
        outRect.left = verticalSpaceHeight;
        outRect.right = verticalSpaceHeight;
    }
}
