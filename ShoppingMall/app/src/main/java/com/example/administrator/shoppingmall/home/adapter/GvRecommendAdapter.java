package com.example.administrator.shoppingmall.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.shoppingmall.R;
import com.example.administrator.shoppingmall.home.bean.HomeResutl;
import com.example.administrator.shoppingmall.utils.Constants;

import java.util.List;

/**
 * Created by Administrator on 2017/10/1.
 */

public class GvRecommendAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<HomeResutl.ResultBean.RecommendInfoBean> data;

    public GvRecommendAdapter(Context mContext, List<HomeResutl.ResultBean.RecommendInfoBean> recommend_info) {
        this.mContext = mContext;
        this.data = recommend_info;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_of_recommend, null);
            viewHolder = new ViewHolder();
            viewHolder.iv_recommend = convertView.findViewById(R.id.iv_recommend);
            viewHolder.tv_name = convertView.findViewById(R.id.tv_name);
            viewHolder.tv_price = convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Glide.with(mContext).load(Constants.IMAGE_BASE_URL + data.get(i).getFigure()).into(viewHolder.iv_recommend);
        viewHolder.tv_name.setText(data.get(i).getName());
        viewHolder.tv_price.setText(data.get(i).getCover_price());
        return convertView;
    }

    static class ViewHolder{
        private ImageView iv_recommend;
        private TextView tv_name;
        private TextView tv_price;
    }
}
