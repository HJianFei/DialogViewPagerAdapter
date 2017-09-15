package com.hjianfei.noticepageradapterdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    PagerDialog pagerDialog;
    private String[] imageList = new String[]{"http://otfl590no.bkt.clouddn.com/1.jpg?attname=",
            "http://otfl590no.bkt.clouddn.com/2.jpg?attname=",
            "http://otfl590no.bkt.clouddn.com/3.jpg?attname=",
            "http://otfl590no.bkt.clouddn.com/4.jpg?attname="};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pagerDialog = new PagerDialog(MainActivity.this, imageList);
                pagerDialog.show();
            }
        });

        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dismiss(MessagEvent messageEvent) {
        pagerDialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
