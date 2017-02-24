package com.taku.pbattle.mvvm.Screens.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.taku.pbattle.mvvm.R;
import com.taku.pbattle.mvvm.ViewModel.Activity.ShowARViewModel;
import com.taku.pbattle.mvvm.databinding.ActivityShowArBinding;

import java.util.Timer;
import java.util.TimerTask;

import eu.kudan.kudan.ARActivity;
import eu.kudan.kudan.ARImageNode;
import eu.kudan.kudan.ARImageTrackable;
import eu.kudan.kudan.ARImageTracker;
import eu.kudan.kudan.ARTextureMaterial;

/**
 * Created by TAKU on 2017/02/24.
 */

public class ShowARActivity extends ARActivity {

    private ActivityShowArBinding binding;
    private ShowARViewModel viewModel;

    private ARImageTrackable mTrackable;
    private ARImageNode mFirstImageNode;
    private ARImageNode mSecondImageNode;

    private Timer mTimer;
    private boolean isShow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_ar);
        super.onCreate(savedInstanceState);
        viewModel = new ShowARViewModel(this);
        binding.setViewModel(viewModel);
        viewModel.onCreate();
        addImageTrackable();
        addImageNode();
        mTimer = new Timer();
        int timeSec = 2000;
        final TimerTask timerTask = new TimerTask() { //2000ミリ秒ごとに処理をかける
            @Override
            public void run() {
                if (mTrackable.getDetected()) { //ARマーカーが表示されたら起動
                    if (isShow) {
                        mTrackable.getWorld().getChildren().get(0).setVisible(false);
                        mTrackable.getWorld().getChildren().get(1).setVisible(true);
                        mTimer.cancel();
                    } else {
                        isShow = true;
                    }
                }
            }
        };
        mTimer.scheduleAtFixedRate(timerTask, 0, timeSec);
    }

    //ARの対象となる画像を設定
    private void addImageTrackable() {
        mTrackable = new ARImageTrackable("target");
        mTrackable.loadFromAsset("target.png");

        ARImageTracker trackableManager = ARImageTracker.getInstance();
        trackableManager.addTrackable(mTrackable);
    }

    //表示するARオブジェクトを設定
    private void addImageNode() {
        mFirstImageNode = new ARImageNode("search.png");
        mSecondImageNode = new ARImageNode("complete.png");

        mTrackable.getWorld().addChild(mFirstImageNode);
        mTrackable.getWorld().addChild(mSecondImageNode);

        ARTextureMaterial textureMaterial = (ARTextureMaterial) mFirstImageNode.getMaterial();
        float scale = mTrackable.getWidth() / textureMaterial.getTexture().getWidth();
        mFirstImageNode.scaleByUniform(scale);
        mSecondImageNode.scaleByUniform(scale);

        mTrackable.getWorld().getChildren().get(0).setVisible(true);
        mTrackable.getWorld().getChildren().get(1).setVisible(false);
    }
}
