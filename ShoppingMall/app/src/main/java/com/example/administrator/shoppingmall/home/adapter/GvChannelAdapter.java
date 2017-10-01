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
 * Created by Administrator on 2017/9/30.
 */

public class GvChannelAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<HomeResutl.ResultBean.ChannelInfoBean> channel_info;

    public GvChannelAdapter(Context mContext, List<HomeResutl.ResultBean.ChannelInfoBean> channel_info) {
        this.mContext = mContext;
        this.channel_info = channel_info;
    }

    @Override
    public int getCount() {
        return channel_info.size();
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
            convertView = View.inflate(mContext, R.layout.item_of_gvchannel, null);
            viewHolder = new ViewHolder();
            viewHolder.ivChannelIcon = convertView.findViewById(R.id.iv_channel_icon);
            viewHolder.tvChannelName = convertView.findViewById(R.id.tv_channel_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //根据位置得到对应的数据
        HomeResutl.ResultBean.ChannelInfoBean channelBean = channel_info.get(i);
        Glide.with(mContext).load(Constants.IMAGE_BASE_URL + channelBean.getImage()).into(viewHolder.ivChannelIcon);
        viewHolder.tvChannelName.setText(channelBean.getChannel_name());
        return convertView;
    }

    static class ViewHolder{
        ImageView ivChannelIcon;
        TextView tvChannelName;
    }
}
