package com.example.administrator.shoppingmall.user;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.shoppingmall.base.BaseFragment;

/**
 * Created by Administrator on 2017/9/29.
 */

public class UserFragment extends BaseFragment {
    private TextView textView;


    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLUE);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();

        textView.setText("用户");

    }
}
