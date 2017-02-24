package com.taku.pbattle.mvvm.Screens.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.taku.pbattle.mvvm.R;
import com.taku.pbattle.mvvm.databinding.ItemListBinding;

/**
 * Created by TAKU on 2017/02/24.
 */

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {

    private String[] mItemList;

    public ItemListAdapter(String[] itemList) {
        mItemList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String data = mItemList[position];
        holder.binding.title.setText(data);
    }

    @Override
    public int getItemCount() {
        return mItemList.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemListBinding binding;

        ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public ItemListBinding getBinding() {
            return binding;
        }
    }
}
