package com.mkttestprojects.trainingapplication.common;

import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaungkhantsoe on 24/08/2020.
 **/
abstract class BaseDiffUtil extends DiffUtil.Callback {

    List<Pageable> mOldItemList;
    List<Pageable> mNewItemList;

    public BaseDiffUtil(List<Pageable> mOldItemList, List<Pageable> mNewItemList) {
        this.mOldItemList = mOldItemList == null ? new ArrayList<Pageable>() : mOldItemList;
        this.mNewItemList = mNewItemList == null ? new ArrayList<Pageable>() : mNewItemList;
    }

    public List<Pageable> getmOldItemList() {
        return mOldItemList;
    }

    public List<Pageable> getmNewItemList() {
        return mNewItemList;
    }

    @Override
    public int getOldListSize() {
        return mOldItemList == null ? 0 : mOldItemList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewItemList == null ? 0 : mNewItemList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return areTheSame(oldItemPosition,newItemPosition);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldItemList.get(oldItemPosition).equals(mNewItemList.get(newItemPosition));
    }

    protected abstract boolean areTheSame(int oldItemPosition, int newItemPosition);
}
