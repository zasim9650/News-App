package com.example.indianewsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class SplashActivity extends AppCompatActivity {
    private ImageView image;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        image = (ImageView)  findViewById(R.id.splash_image);


        Animation animationUtils=  AnimationUtils.loadAnimation(SplashActivity.this,R.anim.fade_in);
        image.startAnimation(animationUtils);


       final Handler handler = new Handler();
       handler.postDelayed(new Runnable() {
           @Override
           public void run() {
               Intent intent =new Intent(SplashActivity.this,MainActivity.class);
               startActivity(intent);


           }
       },3000);
    }


}