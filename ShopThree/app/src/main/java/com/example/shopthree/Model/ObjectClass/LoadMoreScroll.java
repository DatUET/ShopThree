package com.example.shopthree.Model.ObjectClass;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class LoadMoreScroll extends RecyclerView.OnScrollListener {

    int itemandautien = 0;
    int tongitem = 0;
    int itemloadtruoc = 10;
    RecyclerView.LayoutManager layoutManager;
    ILoadMore iLoadMore;

    public LoadMoreScroll(RecyclerView.LayoutManager layoutManager, ILoadMore iLoadMore) {
        this.layoutManager = layoutManager;
        this.iLoadMore = iLoadMore;
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        tongitem = layoutManager.getItemCount();
        if(layoutManager instanceof LinearLayoutManager)
        {
            itemandautien = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
            tongitem = layoutManager.getItemCount();
        }
        else if(layoutManager instanceof GridLayoutManager)
        {
            itemandautien = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
            tongitem = layoutManager.getItemCount();
        }

        if(tongitem <= (itemandautien + itemloadtruoc) && tongitem > 0)
        {
            iLoadMore.LoadMore(tongitem);
        }
    }
}
