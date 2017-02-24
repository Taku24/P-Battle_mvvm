package com.taku.pbattle.mvvm.Screens.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ArrayAdapter;

import com.taku.pbattle.mvvm.Model.DrawerItem;
import com.taku.pbattle.mvvm.R;
import com.taku.pbattle.mvvm.databinding.ItemDrawerBinding;

import java.util.List;

/**
 * Created by TAKU on 2017/02/24.
 */

public class DrawerAdapter extends ArrayAdapter<DrawerItem> {

    private List<DrawerItem> mNavItems;
    private Context mContext;

    public DrawerAdapter(Context context, int resource, List<DrawerItem> navItems) {
        super(context, resource);
        mContext = context;
        mNavItems = navItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemDrawerBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_drawer, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (ItemDrawerBinding) convertView.getTag();
        }
        binding.title.setText(mNavItems.get(position).getAction());
        return convertView;
    }

    public int getCount() {
        return mNavItems.size();
    }

}
