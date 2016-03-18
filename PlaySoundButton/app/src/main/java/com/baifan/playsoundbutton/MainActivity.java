package com.baifan.playsoundbutton;

import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnPlaySound;

    private ImageView mImgLoading;

    private ObjectAnimator mObjectAnimator;

    private ImageView mImgPlaySound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initEvents();
    }

    /**
     * 初始化事件
     */
    private void initEvents() {
        mBtnPlaySound.setOnClickListener(this);
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        mBtnPlaySound = (Button) findViewById(R.id.btn_playSound);
        mImgLoading = (ImageView) findViewById(R.id.img_loading);
        mImgPlaySound = (ImageView) findViewById(R.id.img_play);
    }

    @Override
    public void onClick(View v) {
        mBtnPlaySound.setEnabled(false);
        startAnim();
        startPlaySoundAnim();
        mHandler.sendEmptyMessageDelayed(0, 5000);
    }

    /**
     * 开始动画
     */
    private void startAnim() {
        mImgLoading.setVisibility(View.VISIBLE);
        mObjectAnimator = ObjectAnimator.ofFloat(mImgLoading, "rotation", 0, 360).setDuration(2000);
        mObjectAnimator.setRepeatCount(-1);
        mObjectAnimator.setInterpolator(new LinearInterpolator());
//        mObjectAnimator.setRepeatMode(Animation.REVERSE);
        mObjectAnimator.start();
    }

    private void stopAnim() {
        mObjectAnimator.end();
        mImgLoading.setVisibility(View.GONE);


    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            stopAnim();
            mBtnPlaySound.setEnabled(true);
            mImgPlaySound.setImageResource(R.drawable.adj);
        }
    };

    /**
     * 播放音频的动画
     */
    private void startPlaySoundAnim(){
        mImgPlaySound.setImageResource(R.drawable.anim_play_sound);
        AnimationDrawable animationDrawable = (AnimationDrawable) mImgPlaySound.getDrawable();
        animationDrawable.start();
    }
}
