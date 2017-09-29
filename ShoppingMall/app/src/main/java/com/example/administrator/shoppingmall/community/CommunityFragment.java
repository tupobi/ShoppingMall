package com.example.administrator.shoppingmall.community;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.shoppingmall.base.BaseFragment;

/**
 * Created by Administrator on 2017/9/29.
 */

public class CommunityFragment extends BaseFragment {
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

        textView.setText("发现");

    }
}
