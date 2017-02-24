package com.taku.pbattle.mvvm.Screens.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.taku.pbattle.mvvm.Constans;
import com.taku.pbattle.mvvm.R;
import com.taku.pbattle.mvvm.Screens.Adapter.ItemListAdapter;
import com.taku.pbattle.mvvm.ViewModel.Activity.ListViewModel;
import com.taku.pbattle.mvvm.databinding.ActivityListBinding;

/**
 * Created by TAKU on 2017/02/24.
 */

public class ListActivity extends DrawerActivity {

    private ActivityListBinding binding;
    private ListViewModel viewModel;
    private ItemListAdapter mItemListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list);
        super.onCreate(savedInstanceState);
        viewModel = new ListViewModel(this);
        binding.setViewModel(viewModel);
        viewModel.onCreate();
        binding.title.setText(getText(R.string.list_title));
        mItemListAdapter = new ItemListAdapter(Constans.DataList.DATA_LIST);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(mItemListAdapter);
        binding.recyclerView.setNestedScrollingEnabled(false);
    }
}
