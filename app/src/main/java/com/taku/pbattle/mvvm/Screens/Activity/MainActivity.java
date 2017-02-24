package com.taku.pbattle.mvvm.Screens.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.taku.pbattle.mvvm.Constans;
import com.taku.pbattle.mvvm.R;
import com.taku.pbattle.mvvm.ViewModel.Activity.MainViewModel;
import com.taku.pbattle.mvvm.databinding.ActivityMainBinding;

import eu.kudan.kudan.ARAPIKey;

public class MainActivity extends DrawerActivity implements OnMapReadyCallback {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        super.onCreate(savedInstanceState);
        viewModel = new MainViewModel(this);
        binding.setViewModel(viewModel);
        viewModel.onCreate();
        binding.title.setText(getText(R.string.main_title));
        binding.map.onCreate(savedInstanceState);
        binding.map.getMapAsync(this);
        ARAPIKey key = ARAPIKey.getInstance();
        key.setAPIKey(Constans.APIKey.KUDAN_AR);
        binding.startAr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowARActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.map.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.map.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        binding.map.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng objectLocation = new LatLng(34.702485, 135.495951);
        googleMap.addMarker(new MarkerOptions()
                .position(objectLocation)
                .title("大阪駅")
                .snippet("JRの大阪駅")
        );
        for(int i = 0; i < 3; i += 1){
            googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(Constans.DataList.LNG_LIST[i], Constans.DataList.LAT_LIST[i]))
                    .title(Constans.DataList.NAME_LIST[i])
                    .snippet(Constans.DataList.CONTENT_LIST[i])
            );
        }
        float zoom = 13;
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(objectLocation, zoom));
        googleMap.getUiSettings().setMapToolbarEnabled(false);
        googleMap.getUiSettings().setRotateGesturesEnabled(false);

    }
}
