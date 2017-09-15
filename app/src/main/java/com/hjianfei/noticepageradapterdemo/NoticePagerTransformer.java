package com.hjianfei.noticepageradapterdemo;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * <pre>
 *     author : Administrator
 *     e-mail : 190766172@qq.com
 *     time   : 2017-09-15
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class NoticePagerTransformer implements ViewPager.PageTransformer {

    private static final float ROX_MAX = 20.0f;
    private float mRox;

    @Override
    public void transformPage(View page, float position) {
        if (position < -1) {    //页面不可见

            ViewCompat.setRotation(page, 0);
        } else if (position < 0) { //页面可见，页面的左边界已经画出屏幕

            mRox = (position * ROX_MAX);
            ViewCompat.setPivotX(page, page.getMeasuredWidth());
            ViewCompat.setPivotY(page, page.getMeasuredHeight());
            ViewCompat.setRotation(page, mRox);

        } else if (position < 1) {  //页面可见，页面的左边界已经进入屏幕

            mRox = (position * ROX_MAX);
            ViewCompat.setPivotX(page, 0);
            ViewCompat.setPivotY(page, page.getMeasuredHeight());
            ViewCompat.setRotation(page, mRox);

        } else {  //页面不可见

            ViewCompat.setRotation(page, 0);
        }
    }
}
