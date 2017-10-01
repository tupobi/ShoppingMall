package com.example.administrator.shoppingmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.shoppingmall.R;
import com.example.administrator.shoppingmall.home.bean.HomeResutl;
import com.example.administrator.shoppingmall.utils.Constants;

import java.util.List;

/**
 * Created by Administrator on 2017/9/30.
 */

public class RvSeckillAdapter extends RecyclerView.Adapter<RvSeckillAdapter.ViewHolder> {


    private final Context mContext;
    private final List<HomeResutl.ResultBean.SeckillInfoBean.ListBean> listData;

    public RvSeckillAdapter(Context mContext, List<HomeResutl.ResultBean.SeckillInfoBean.ListBean> list) {
        this.mContext = mContext;
        this.listData = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_of_rvseckill, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //1、根据位置得到对应的数据
        HomeResutl.ResultBean.SeckillInfoBean.ListBean listBean = listData.get(position);

        // 2、绑定数据
        Glide.with(mContext).load(Constants.IMAGE_BASE_URL + listBean.getFigure()).into(holder.iv_figure);
        holder.tv_origin_price.setText(listBean.getOrigin_price());
        holder.tv_cover_price.setText(listBean.getCover_price());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_figure;
        private TextView tv_cover_price;
        private TextView tv_origin_price;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_figure = itemView.findViewById(R.id.iv_figure);
            tv_cover_price = itemView.findViewById(R.id.tv_cover_price);
            tv_origin_price = itemView.findViewById(R.id.tv_origin_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onSeckillRecyclerView != null) {
                        onSeckillRecyclerView.onItemClickListenter(getLayoutPosition());
                    }
                }
            });
        }
    }

    public interface OnSeckillRecyclerView{
        public void onItemClickListenter(int position);
    }

    private OnSeckillRecyclerView onSeckillRecyclerView;

    public void setOnSeckillRecyclerView(OnSeckillRecyclerView onSeckillRecyclerView) {
        this.onSeckillRecyclerView = onSeckillRecyclerView;
    }
}
