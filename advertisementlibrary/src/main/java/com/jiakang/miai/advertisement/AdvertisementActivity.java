package com.jiakang.miai.advertisement;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.shizhefei.view.largeimage.LargeImageView;
import com.shizhefei.view.largeimage.factory.FileBitmapDecoderFactory;

import java.util.Timer;
import java.util.TimerTask;

public class AdvertisementActivity extends AppCompatActivity {
    private Button btnskip;
    private ImageView imgadvertisement;
    private Timer timer;
    private int recLen =6;
    private Intent intent;
    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    btnskip.setText( recLen+"跳过");
                    if (recLen== 0) {
                        timer.cancel();
                        finish();
                    }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        setContentView(R.layout.activity_advertisement);
        init();
        initOnClick();
        showAdvertisement();
    }


    private void init() {
        btnskip = (Button) findViewById(R.id.btnskip);
        btnskip.getBackground().setAlpha(125);
        imgadvertisement = (ImageView) findViewById(R.id.imgadvertisement);
        timer = new Timer();

    }

    private void initOnClick() {

        imgadvertisement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent =new Intent();
                intent.setClass(AdvertisementActivity.this,WebViewActivity.class);
                startActivity(intent);
            }
        });
        btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  finish();
            }
        });
    }

    private void showAdvertisement() {

        timer.schedule(task, 1000, 1000);       // timeTask
    }



    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            recLen--;
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    };
}
