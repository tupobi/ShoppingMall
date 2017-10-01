package com.example.administrator.shoppingmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
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

public class RvHotAdapter extends RecyclerView.Adapter<RvHotAdapter.ViewHolder>{

    private final Context mContext;
    private final List<HomeResutl.ResultBean.HotInfoBean> data;

    public RvHotAdapter(Context mContext, List<HomeResutl.ResultBean.HotInfoBean> hot_info) {
        this.mContext = mContext;
        this.data = hot_info;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(mContext, R.layout.item_of_rvhot , null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HomeResutl.ResultBean.HotInfoBean itemData = data.get(position);
        Glide.with(mContext).load(Constants.IMAGE_BASE_URL + itemData.getFigure()).into(holder.iv_hot);
        holder.tv_price.setText(itemData.getCover_price());
        holder.tv_name.setText(itemData.getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_hot;
        private TextView tv_name;
        private TextView tv_price;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_hot = itemView.findViewById(R.id.iv_hot);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onHotRecyclerViewClick != null) {
                        onHotRecyclerViewClick.onItemClickListenter(getLayoutPosition());
                    }
                }
            });

        }
    }

    public interface OnHotRecyclerViewClick {
        public void onItemClickListenter(int position);
    }

    private RvHotAdapter.OnHotRecyclerViewClick onHotRecyclerViewClick;

    public void setOnHotRecyclerViewClick(RvHotAdapter.OnHotRecyclerViewClick onHotRecyclerViewClick) {
        this.onHotRecyclerViewClick = onHotRecyclerViewClick;
    }

}
