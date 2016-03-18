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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 播放声音的按钮
     */
    private Button mBtnPlaySound;

    /**
     * 正在播放的按钮
     */
    private ImageView mImgLoading;

    /**
     * 正在播放时需要的动画
     */
    private ObjectAnimator mObjectAnimator;

    /**
     * 播放声音时的图片
     */
    private ImageView mImgPlaySound;
    /**
     * 音乐描述
     */
    private TextView mTvSoundDescribe;

    /**
     * 删除音乐按钮
     */
    private ImageButton mImgDel;

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
        mImgDel.setOnClickListener(this);
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        mImgDel = (ImageButton) findViewById(R.id.img_del);
        mBtnPlaySound = (Button) findViewById(R.id.btn_playSound);
        mImgLoading = (ImageView) findViewById(R.id.img_loading);
        mImgPlaySound = (ImageView) findViewById(R.id.img_play);
        mTvSoundDescribe = (TextView) findViewById(R.id.tv_sound_describe);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_playSound:
                mBtnPlaySound.setEnabled(false);
                startAnim();
                startPlaySoundAnim();
                mHandler.sendEmptyMessageDelayed(0, 5000);
                break;
            case R.id.img_del:
                Toast.makeText(this, "!!!!", Toast.LENGTH_LONG).show();
                break;
        }
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
    private void startPlaySoundAnim() {
        mImgPlaySound.setImageResource(R.drawable.anim_play_sound);
        AnimationDrawable animationDrawable = (AnimationDrawable) mImgPlaySound.getDrawable();
        animationDrawable.start();
    }


    private void setSoundDescribe(String text) {
        mTvSoundDescribe.setVisibility(View.VISIBLE);
        mTvSoundDescribe.setText(text);
    }
}
