package com.taku.pbattle.mvvm.Screens.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.taku.pbattle.mvvm.R;
import com.taku.pbattle.mvvm.ViewModel.Activity.SettingViewModel;
import com.taku.pbattle.mvvm.databinding.ActivitySettingBinding;

/**
 * Created by TAKU on 2017/02/24.
 */

public class SettingActivity extends DrawerActivity{

    private ActivitySettingBinding binding;
    private SettingViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting);
        super.onCreate(savedInstanceState);
        viewModel = new SettingViewModel(this);
        binding.setViewModel(viewModel);
        viewModel.onCreate();
        binding.title.setText(getText(R.string.setting_title));
    }
}
