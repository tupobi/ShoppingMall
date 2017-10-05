package com.example.administrator.shoppingmall.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.shoppingmall.R;
import com.example.administrator.shoppingmall.home.bean.GoodsBean;
import com.example.administrator.shoppingmall.shoppingcart.utils.CartStorage;
import com.example.administrator.shoppingmall.utils.Constants;

public class AtyGoodsInfo extends Activity implements View.OnClickListener {

    private ImageButton ibGoodInfoBack;
    private ImageButton ibGoodInfoMore;
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoDesc;
    private TextView tvGoodInfoPrice;
    private TextView tvGoodInfoStore;
    private TextView tvGoodInfoStyle;
    private WebView wbGoodInfoMore;
    private LinearLayout llGoodsRoot;
    private TextView tvGoodInfoCallcenter;
    private TextView tvGoodInfoCollection;
    private TextView tvGoodInfoCart;
    private Button btnGoodInfoAddcart;

    private TextView tv_more_share;
    private TextView tv_more_search;
    private TextView tv_more_home;
    private Button btn_more;
    private GoodsBean goodsBean;


    public static void actionStart(Context context, GoodsBean goodsBean) {
        Intent intent = new Intent(context, AtyGoodsInfo.class);
        intent.putExtra("goods_bean", goodsBean);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v == ibGoodInfoBack) {
            finish();
        } else if (v == ibGoodInfoMore) {
            Toast.makeText(this, "更多信息", Toast.LENGTH_SHORT).show();
        } else if (v == btnGoodInfoAddcart) {
            CartStorage.getInstance().addGoods(goodsBean);
            Toast.makeText(this, "添加进购物车成功！", Toast.LENGTH_SHORT).show();
        } else if (v == tvGoodInfoCallcenter) {
            Toast.makeText(this, "呼叫客服", Toast.LENGTH_SHORT).show();
        }else if (v == tvGoodInfoCollection) {
            Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
        }else if (v == tvGoodInfoCart) {
            Toast.makeText(this, "跳转到购物车", Toast.LENGTH_SHORT).show();
        } else if (v == tv_more_share) {
            Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
        } else if (v == tv_more_search) {
            Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
        } else if (v == tv_more_home) {
            Toast.makeText(this, "主页", Toast.LENGTH_SHORT).show();
        } else if (v == btn_more) {
            Toast.makeText(this, "更多", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_goods_info);

        findViews();
        setWidgetData();
    }

    private void setWidgetData() {
        goodsBean = (GoodsBean) getIntent().getSerializableExtra("goods_bean");
        if (goodsBean != null) {
            Glide.with(AtyGoodsInfo.this).load(Constants.IMAGE_BASE_URL + goodsBean.getFigure()).into(ivGoodInfoImage);
            tvGoodInfoName.setText(goodsBean.getName());
            tvGoodInfoPrice.setText("¥ " + goodsBean.getCover_price());
        } else {
            Toast.makeText(this, "没有接受到数据！", Toast.LENGTH_SHORT).show();
        }

        WebSettings wbSettings = wbGoodInfoMore.getSettings();
        //设置支持javaScript
        wbSettings.setJavaScriptEnabled(true);
        //设置双击变大变小
        wbSettings.setUseWideViewPort(true);
        //设置缩放按钮
        wbSettings.setBuiltInZoomControls(true);
        //不让从当前网页跳转到系统浏览器中
        wbGoodInfoMore.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                pbLoading.setVisibility(View.GONE);

            }
        });
        wbGoodInfoMore.loadUrl("http://github.com/");
    }


    private void findViews() {

        tv_more_share = findViewById(R.id.tv_more_share);
        tv_more_search = findViewById(R.id.tv_more_search);
        tv_more_home = findViewById(R.id.tv_more_home);
        btn_more = findViewById(R.id.btn_more);

        ibGoodInfoBack = (ImageButton) findViewById(R.id.ib_good_info_back);
        ibGoodInfoMore = (ImageButton) findViewById(R.id.ib_good_info_more);
        ivGoodInfoImage = (ImageView) findViewById(R.id.iv_good_info_image);
        tvGoodInfoName = (TextView) findViewById(R.id.tv_good_info_name);
        tvGoodInfoDesc = (TextView) findViewById(R.id.tv_good_info_desc);
        tvGoodInfoPrice = (TextView) findViewById(R.id.tv_good_info_price);
        tvGoodInfoStore = (TextView) findViewById(R.id.tv_good_info_store);
        tvGoodInfoStyle = (TextView) findViewById(R.id.tv_good_info_style);
        wbGoodInfoMore = (WebView) findViewById(R.id.wb_good_info_more);
        llGoodsRoot = (LinearLayout) findViewById(R.id.ll_goods_root);
        tvGoodInfoCallcenter = (TextView) findViewById(R.id.tv_good_info_callcenter);
        tvGoodInfoCollection = (TextView) findViewById(R.id.tv_good_info_collection);
        tvGoodInfoCart = (TextView) findViewById(R.id.tv_good_info_cart);
        btnGoodInfoAddcart = (Button) findViewById(R.id.btn_good_info_addcart);

        ibGoodInfoBack.setOnClickListener(this);
        ibGoodInfoMore.setOnClickListener(this);
        btnGoodInfoAddcart.setOnClickListener(this);

        tvGoodInfoCallcenter.setOnClickListener(this);
        tvGoodInfoCollection.setOnClickListener(this);
        tvGoodInfoCart.setOnClickListener(this);

        tv_more_share.setOnClickListener(this);
        tv_more_search.setOnClickListener(this);
        tv_more_home.setOnClickListener(this);
        btn_more.setOnClickListener(this);
    }
}
