package com.hjianfei.noticepageradapterdemo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : Administrator
 *     e-mail : 190766172@qq.com
 *     time   : 2017-09-15
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class PagerDialog extends Dialog {
    private String[] images ;

    private ViewPager viewPager = null;
    private ImageView dismissImgView = null;

    public PagerDialog(@NonNull Context context, String[] images) {
        super(context,R.style.pagerDialog);
        this.images = images;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置动画效果
        getWindow().setWindowAnimations(R.style.popupAnimation);
        //设置dialog的布局
        setContentView(R.layout.dialog_popup);

        viewPager = (ViewPager)findViewById(R.id.dialog_viewpager);
        dismissImgView = (ImageView)findViewById(R.id.imageview_dismiss_dialog);

        viewPager.setAdapter(createAdapter());
        viewPager.setPageTransformer(true, new NoticePagerTransformer());
        viewPager.setOffscreenPageLimit(images.length);
        dismissImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessagEvent());
            }
        });
    }

    private PagerAdapter createAdapter(){
        List<View> views = new ArrayList<>();
        for (int i = 0; i < images.length; i ++){
            final int j = i;
            ImageView[] mDots = new ImageView[images.length];
            int dp1 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,7 ,
                    getContext().getResources().getDisplayMetrics());
            int dp2 = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5,
                    getContext().getResources().getDisplayMetrics());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    dp1,dp1);

            View parent = LayoutInflater.from(getContext()).inflate(R.layout.item_pager, null);
            ImageView imageView = (ImageView)parent.findViewById(R.id.imageView);
            LinearLayout dotsLayout = (LinearLayout)parent.findViewById(R.id.dots_layout);
            Glide.with(getContext()).load(images[i]).into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("wnw", "img = " + j);
                }
            });

            //设置圆点
            if (images.length > 0 && images.length != 1){
                for (int k = 0; k < images.length; k++){
                    mDots[k] = new ImageView(getContext());
                    mDots[k].setBackgroundResource(R.drawable.dialog_dot_selector);
                    params.leftMargin = dp2;// 设置圆点间隔
                    params.rightMargin = dp2;// 设置圆点间隔
                    if (k == i){
                        mDots[k].setSelected(true);
                    }else {
                        mDots[k].setSelected(false);
                    }
                    mDots[k].setLayoutParams(params);
                    dotsLayout.addView(mDots[k], k);
                }
            }
            views.add(parent);
        }
        return new NoticePagerAdapter(views);
    }

    @Override
    public void show() {
        if(images.length > 0){
            super.show();
        }
    }
}
