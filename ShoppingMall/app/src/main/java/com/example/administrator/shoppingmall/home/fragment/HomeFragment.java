package com.example.administrator.shoppingmall.home.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.shoppingmall.R;
import com.example.administrator.shoppingmall.base.BaseFragment;
import com.example.administrator.shoppingmall.home.adapter.RvHomeAdapter;
import com.example.administrator.shoppingmall.home.bean.HomeResutl;
import com.example.administrator.shoppingmall.utils.Constants;
import com.example.administrator.shoppingmall.utils.LogUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/9/29.
 */

public class HomeFragment extends BaseFragment {

    private RecyclerView rv_home;
    private FloatingActionButton fab_return_top;
    private TextView tv_search_home;
    private TextView tv_message_home;
    private RvHomeAdapter rvHomeAdapter;

    /**
     * 主页的数据
     */
    private HomeResutl.ResultBean homeResultBean;


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        initWidget(view);

        return view;
    }

    private void initWidget(View view) {
        rv_home = view.findViewById(R.id.rv_home);
        fab_return_top = view.findViewById(R.id.fab_return_top);
        tv_search_home = view.findViewById(R.id.tv_search_home);
        tv_message_home = view.findViewById(R.id.tv_message_home);

        fab_return_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "返回顶部", Toast.LENGTH_SHORT).show();
                rv_home.scrollToPosition(0);
            }
        });

        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
            }
        });

        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "消息", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();

        getDataFromNet();
    }

    private void getDataFromNet() {
        String url = Constants.HOME_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e("联网请求失败：" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("联网请求成功!");
                        processData(response);
                    }
                });
    }

    private void processData(String jsonData) {
        homeResultBean = new Gson().fromJson(jsonData, HomeResutl.class).getResult();
        if (homeResultBean != null) {
            //有数据，设置适配器
            rvHomeAdapter = new RvHomeAdapter(mContext, homeResultBean);
            rv_home.setAdapter(rvHomeAdapter);
            GridLayoutManager manager = new GridLayoutManager(mContext, 1);
            rv_home.setLayoutManager(manager);
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position <= 3) {
                        fab_return_top.setVisibility(View.GONE);
                    } else {
                        fab_return_top.setVisibility(View.VISIBLE);
                    }
                    return 1;//跨度为1，大于1会崩溃
                }
            });

        } else {
            //没有数据
            LogUtil.e("homeResultBean没有数据！");
        }
    }
}
