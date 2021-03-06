package com.mkttestprojects.trainingapplication.common;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by aung on 11/26/17.
 */

public class SmartScrollListener extends RecyclerView.OnScrollListener {

    public interface OnSmartScrollListener {
        void onListEndReach();
    }

    private boolean isListEndReached = false;

    private boolean canScrollMore = true;

    private OnSmartScrollListener mSmartScrollListener;

    public SmartScrollListener(OnSmartScrollListener smartScrollListener) {
        this.mSmartScrollListener = smartScrollListener;
    }

    public void setCanScrollMore(boolean canScrollMore) {
        this.canScrollMore = canScrollMore;
    }

    @Override
    public void onScrolled(RecyclerView rv, int dx, int dy) {
        super.onScrolled(rv, dx, dy);

        int visibleItemCount = rv.getLayoutManager().getChildCount();
        int totalItemCount = rv.getLayoutManager().getItemCount();
        int pastVisibleItems = ((LinearLayoutManager) rv.getLayoutManager()).findFirstVisibleItemPosition();

        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
            isListEndReached = false;
        }
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
        super.onScrollStateChanged(recyclerView, scrollState);
        if (scrollState == RecyclerView.SCROLL_STATE_IDLE
                && ((LinearLayoutManager) recyclerView.getLayoutManager())
                .findLastVisibleItemPosition() == recyclerView.getAdapter().getItemCount() - 1
                && !isListEndReached && canScrollMore) {
            isListEndReached = true;
            mSmartScrollListener.onListEndReach();
        }
    }
}
