package com.example.administrator.shoppingmall.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.administrator.shoppingmall.R;
import com.example.administrator.shoppingmall.app.AtyMain;

import java.util.Timer;
import java.util.TimerTask;

public class AtySplash extends Activity {
    private TextView tvCountDown;
    private Handler handler;
    private Timer timer;
    private TimerTask timerTask;
    private int countDown = 6;
    private boolean isMainLaunched = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        launchAtyMain();
    }

    private void launchAtyMain() {
        handler = new Handler();
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        countDown--;
                        tvCountDown.setText(countDown + "秒 跳过");
                        if (countDown < 0) {
                            timer.cancel();
                        }
                    }
                });
            }
        };

        timer.schedule(timerTask, 1000, 1000);  //timerTask不能为空，已经new出来才行。

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //推迟4秒后要做的事情
                //handler被removeCallbacksAndMessages(null)后，所有消息都不再执行。
                if (!isMainLaunched)
                    startAtyMain();
            }
        }, 6 * 1000);

    }

    private void startAtyMain() {
        AtyMain.actionStart(this, this);
    }

    private void initView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.aty_splash);

        tvCountDown = findViewById(R.id.tv_CountDown);

        tvCountDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAtyMain();
                isMainLaunched = true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        //finish()后，移除handler，否则handler继续执行启动主Aty，也可以判断解除
    }
}