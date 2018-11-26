package com.example.prideland.foodrecipies;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    Animation uptodown, downtoup;

    @BindView(R.id.appnamesplashtext)
    TextView mAppnamesplashtext;
    @BindView(R.id.uptodown)
    LinearLayout mUptodown;
    @BindView(R.id.downtoup)
    LinearLayout mDowntoup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);


        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        mUptodown.setAnimation(uptodown);
        mDowntoup.setAnimation(downtoup);


//        Typeface righteousFonts = Typeface.createFromAsset(getAssets(), "fonts/Righteous/Righteous-Regular.ttf");
//        mAppnamesplashtext.setTypeface(righteousFonts);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);

    }

}
