package com.example.administrator.shoppingmall.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.example.administrator.shoppingmall.R;
import com.example.administrator.shoppingmall.base.BaseFragment;
import com.example.administrator.shoppingmall.community.CommunityFragment;
import com.example.administrator.shoppingmall.home.HomeFragment;
import com.example.administrator.shoppingmall.shoppingcart.ShoppingCartFragment;
import com.example.administrator.shoppingmall.type.TypeFragment;
import com.example.administrator.shoppingmall.user.UserFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/29.
 */

public class AtyMain extends FragmentActivity {

    public static void actionStart(Context context, Activity activity) {
        context.startActivity(new Intent(context, AtyMain.class));
        activity.finish();
    }

    private RadioGroup rgMain;
    private ArrayList<BaseFragment> fragments;
    private int position;
    private TypeFragment typeFragment;
    private BaseFragment previousFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_main);

        rgMain = findViewById(R.id.rg_main);

        initFragment();
        initListener();
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        typeFragment = new TypeFragment();
        fragments.add(new HomeFragment());
        fragments.add(typeFragment);
        fragments.add(new CommunityFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new UserFragment());
    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_type:

                        position = 1;
                        break;
                    case R.id.rb_community:
                        position = 2;

                        break;
                    case R.id.rb_cart:
                        position = 3;

                        break;
                    case R.id.rb_user:
                        position = 4;
                        break;
                }

                BaseFragment baseFragment = getFragment(position);
                switchFragment(previousFragment, baseFragment);
            }
        });
        rgMain.check(R.id.rb_home);
    }


    /**
     * @param position
     * @return
     */
    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (previousFragment != nextFragment) {
            previousFragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }

}