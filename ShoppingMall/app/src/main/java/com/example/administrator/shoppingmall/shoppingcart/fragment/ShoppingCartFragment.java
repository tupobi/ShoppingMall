package com.example.administrator.shoppingmall.shoppingcart.fragment;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.shoppingmall.R;
import com.example.administrator.shoppingmall.base.BaseFragment;
import com.example.administrator.shoppingmall.home.bean.GoodsBean;
import com.example.administrator.shoppingmall.shoppingcart.adapter.RvShoppingCartAdapter;
import com.example.administrator.shoppingmall.shoppingcart.utils.CartStorage;
import com.example.administrator.shoppingmall.utils.LogUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/9/29.
 */

public class ShoppingCartFragment extends BaseFragment implements View.OnClickListener {

    private TextView tvShopcartEdit;
    private RecyclerView recyclerview;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private CheckBox cbAll;
    private Button btnDelete;
    private Button btnCollection;

    private LinearLayout llEmptyShopcart;
    private ImageView ivEmpty;
    private TextView tvEmptyCartTobuy;
    private RvShoppingCartAdapter rvShoppingCartAdapter;

    private List<GoodsBean> goodsBeanList;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_shoppingcart, null);

        tvShopcartEdit = (TextView) view.findViewById(R.id.tv_shopcart_edit);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        llCheckAll = view.findViewById(R.id.ll_check_all);
        checkboxAll = (CheckBox) view.findViewById(R.id.checkbox_all);
        tvShopcartTotal = (TextView) view.findViewById(R.id.tv_shopcart_total);
        btnCheckOut = (Button) view.findViewById(R.id.btn_check_out);
        llDelete = (LinearLayout) view.findViewById(R.id.ll_delete);
        cbAll = (CheckBox) view.findViewById(R.id.cb_all);
        btnDelete = (Button) view.findViewById(R.id.btn_delete);
        btnCollection = (Button) view.findViewById(R.id.btn_collection);

        ivEmpty = (ImageView) view.findViewById(R.id.iv_empty);
        tvEmptyCartTobuy = (TextView) view.findViewById(R.id.tv_empty_cart_tobuy);
        llEmptyShopcart = view.findViewById(R.id.ll_empty_shopcart);

        btnCheckOut.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnCollection.setOnClickListener(this);
        tvShopcartEdit.setOnClickListener(this);

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        goodsBeanList = CartStorage.getInstance().getAllData();
        if (goodsBeanList == null || goodsBeanList.size() == 0) {
            llCheckAll.setVisibility(View.GONE);
            tvShopcartEdit.setVisibility(View.GONE);
            recyclerview.setVisibility(View.GONE);
            llEmptyShopcart.setVisibility(View.VISIBLE);

        } else {
            llCheckAll.setVisibility(View.VISIBLE);
            llDelete.setVisibility(View.GONE);
            rvShoppingCartAdapter = new RvShoppingCartAdapter(mContext, goodsBeanList, tvShopcartTotal, checkboxAll, cbAll, btnDelete);
            ((SimpleItemAnimator) recyclerview.getItemAnimator()).setSupportsChangeAnimations(false);
            recyclerview.setAdapter(rvShoppingCartAdapter);
            recyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        }
    }

    public boolean isEdit;

    @Override
    public void onClick(View v) {
        if (v == btnCheckOut) {
            // Handle clicks for btnCheckOut
        } else if (v == btnDelete) {
            // Handle clicks for btnDelete
        } else if (v == btnCollection) {
            // Handle clicks for btnCollection
        } else if (v == tvShopcartEdit) {
            switchEdit();
        }
    }

    private void switchEdit() {
        isEdit = !isEdit;
        if (isEdit) {
            llCheckAll.setVisibility(View.GONE);
            llDelete.setVisibility(View.VISIBLE);
            tvShopcartEdit.setText("完成");


        } else {
            llCheckAll.setVisibility(View.VISIBLE);
            llDelete.setVisibility(View.GONE);
            tvShopcartEdit.setText("编辑");
        }
    }
}
