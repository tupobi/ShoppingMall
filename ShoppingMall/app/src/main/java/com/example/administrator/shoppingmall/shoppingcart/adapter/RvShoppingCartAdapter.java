package com.example.administrator.shoppingmall.shoppingcart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.shoppingmall.R;
import com.example.administrator.shoppingmall.home.bean.GoodsBean;
import com.example.administrator.shoppingmall.shoppingcart.utils.CartStorage;
import com.example.administrator.shoppingmall.utils.Constants;
import com.example.administrator.shoppingmall.utils.LogUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/10/2.
 */

public class RvShoppingCartAdapter extends RecyclerView.Adapter<RvShoppingCartAdapter.ViewHolder> {

    private final CheckBox cbAll;
    private final Button mBtnDelete;
    private TextView tvShopcartTotal;
    private CheckBox checkboxAll;
    private List<GoodsBean> goodBeanList;
    private final Context mContext;

    public RvShoppingCartAdapter(Context mContext, final List<GoodsBean> goodsBeanList, TextView tvShopcartTotal, final CheckBox checkboxAll, final CheckBox cbAll, Button btnDelete) {
        this.mContext = mContext;
        this.goodBeanList = goodsBeanList;
        this.tvShopcartTotal = tvShopcartTotal;
        this.checkboxAll = checkboxAll;
        this.cbAll = cbAll;
        this.mBtnDelete = btnDelete;
//        goodBeanList = CartStorage.getInstance().getAllData();

        boolean isAllChecked = true;
        for (int i = 0; i < goodBeanList.size(); i++) {
            if (goodBeanList.get(i).isSelected() == false) {
                isAllChecked = false;
                break;
            }
        }
        checkboxAll.setChecked(isAllChecked);

        LogUtil.e("isAllChecked == " + isAllChecked);
        cbAll.setChecked(isAllChecked);

        this.checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b = RvShoppingCartAdapter.this.checkboxAll.isChecked();
                if (b == true) {
                    for (int i = 0; i < RvShoppingCartAdapter.this.goodBeanList.size(); i++) {
                        RvShoppingCartAdapter.this.goodBeanList.get(i).setSelected(b);
                        LogUtil.e(RvShoppingCartAdapter.this.goodBeanList.get(i).isSelected() + "");
                        CartStorage.getInstance().updateGoods(RvShoppingCartAdapter.this.goodBeanList.get(i));
                        notifyItemChanged(i);
                    }
                } else {
                    boolean isCheckedAll = true;
                    for (int i = 0; i < RvShoppingCartAdapter.this.goodBeanList.size(); i++) {
                        if (RvShoppingCartAdapter.this.goodBeanList.get(i).isSelected() == false) {
                            isCheckedAll = false;
                            break;
                        }
                    }
                    LogUtil.e("isCheckedAll == " + isCheckedAll);
                    if (isCheckedAll) {
                        for (int i = 0; i < RvShoppingCartAdapter.this.goodBeanList.size(); i++) {
                            RvShoppingCartAdapter.this.goodBeanList.get(i).setSelected(false);
                            LogUtil.e(RvShoppingCartAdapter.this.goodBeanList.get(i).isSelected() + "");
                            CartStorage.getInstance().updateGoods(RvShoppingCartAdapter.this.goodBeanList.get(i));
                            notifyItemChanged(i);
                        }
                    }
                }
            }
        });

        this.cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b = RvShoppingCartAdapter.this.cbAll.isChecked();
                if (b == true) {
                    for (int i = 0; i < RvShoppingCartAdapter.this.goodBeanList.size(); i++) {
                        RvShoppingCartAdapter.this.goodBeanList.get(i).setSelected(b);
                        LogUtil.e(RvShoppingCartAdapter.this.goodBeanList.get(i).isSelected() + "");
                        CartStorage.getInstance().updateGoods(RvShoppingCartAdapter.this.goodBeanList.get(i));
                        notifyItemChanged(i);
                    }
                } else {
                    boolean isCheckedAll = true;
                    for (int i = 0; i < RvShoppingCartAdapter.this.goodBeanList.size(); i++) {
                        if (RvShoppingCartAdapter.this.goodBeanList.get(i).isSelected() == false) {
                            isCheckedAll = false;
                            break;
                        }
                    }
                    LogUtil.e("isCheckedAll == " + isCheckedAll);
                    if (isCheckedAll) {
                        for (int i = 0; i < RvShoppingCartAdapter.this.goodBeanList.size(); i++) {
                            RvShoppingCartAdapter.this.goodBeanList.get(i).setSelected(false);
                            LogUtil.e(RvShoppingCartAdapter.this.goodBeanList.get(i).isSelected() + "");
                            CartStorage.getInstance().updateGoods(RvShoppingCartAdapter.this.goodBeanList.get(i));
                            notifyItemChanged(i);
                        }
                    }
                }
            }
        });
        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0; i<RvShoppingCartAdapter.this.goodBeanList.size(); i++) {
                    if (goodBeanList.get(i).isSelected()) {
                        CartStorage.getInstance().deleteGoods(goodBeanList.get(i));
                        goodBeanList.remove(i);

                        notifyItemRemoved(i);
                    }
                }
                showToatlPrice();
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(mContext, R.layout.item_of_rvshoppingcart, null));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Glide.with(mContext).load(Constants.IMAGE_BASE_URL + goodBeanList.get(position).getFigure()).into(holder.iv_gov);
        holder.tv_desc_gov.setText(goodBeanList.get(position).getName());
        holder.tv_price_gov.setText(goodBeanList.get(position).getCover_price());
        holder.tv_number_of_goods.setText(String.valueOf(goodBeanList.get(position).getNumber()));

        holder.cb_gov.setChecked(goodBeanList.get(position).isSelected());
        showToatlPrice();
        LogUtil.e("item刷新了！！");


        holder.ib_goods_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (goodBeanList.get(position).getNumber() >= 1) {
                    goodBeanList.get(position).setNumber(goodBeanList.get(position).getNumber() + 1);
                    CartStorage.getInstance().updateGoods(goodBeanList.get(position));
                } else {
                    CartStorage.getInstance().addGoods(goodBeanList.get(position));
                }
                holder.tv_number_of_goods.setText(String.valueOf(goodBeanList.get(position).getNumber()));
                showToatlPrice();
            }
        });

        holder.ib_goods_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (goodBeanList.get(position).getNumber() <= 1) {
                    return;
                }
                goodBeanList.get(position).setNumber(goodBeanList.get(position).getNumber() - 1);
                CartStorage.getInstance().updateGoods(goodBeanList.get(position));
//                goodBeanList = CartStorage.getInstance().getAllData();
                holder.tv_number_of_goods.setText(String.valueOf(goodBeanList.get(position).getNumber()));
                showToatlPrice();
            }
        });

        holder.cb_gov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b = holder.cb_gov.isChecked();
                goodBeanList.get(position).setSelected(b);
                LogUtil.e("isSelected == " + goodBeanList.get(position).isSelected());
                CartStorage.getInstance().updateGoods(goodBeanList.get(position));
                showToatlPrice();

                if (b == false) {
                    checkboxAll.setChecked(false);
                    cbAll.setChecked(false);
                } else {
                    boolean isAllChecked = true;
                    for (int i = 0; i < goodBeanList.size(); i++) {
                        if (goodBeanList.get(i).isSelected() == false) {
                            isAllChecked = false;
                            break;
                        }
                    }
                    if (isAllChecked) {
                        checkboxAll.setChecked(isAllChecked);
                        cbAll.setChecked(isAllChecked);
                    }
                }
            }
        });
    }

    private void showToatlPrice() {
        double totalPrice = 0;
        for (int i = 0; i < goodBeanList.size(); i++) {
            if (goodBeanList.get(i).isSelected()) {
                totalPrice += (Double.parseDouble(goodBeanList.get(i).getCover_price()) * goodBeanList.get(i).getNumber());
            }
        }
        tvShopcartTotal.setText(String.valueOf(totalPrice));
    }

    @Override
    public int getItemCount() {
        return goodBeanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox cb_gov;
        private ImageView iv_gov;
        private TextView tv_desc_gov;
        private TextView tv_price_gov;
        private ImageButton ib_goods_add;
        private ImageButton ib_goods_sub;
        private TextView tv_number_of_goods;

        public ViewHolder(View itemView) {
            super(itemView);

            cb_gov = itemView.findViewById(R.id.cb_gov);
            iv_gov = itemView.findViewById(R.id.iv_gov);
            tv_desc_gov = itemView.findViewById(R.id.tv_desc_gov);
            tv_price_gov = itemView.findViewById(R.id.tv_price_gov);
            ib_goods_add = itemView.findViewById(R.id.ib_goods_add);
            ib_goods_sub = itemView.findViewById(R.id.ib_goods_sub);
            tv_number_of_goods = itemView.findViewById(R.id.tv_number_of_goods);

        }
    }
}
