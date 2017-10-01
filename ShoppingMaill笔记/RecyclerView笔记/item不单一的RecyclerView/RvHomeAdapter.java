package com.example.administrator.shoppingmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.shoppingmall.R;
import com.example.administrator.shoppingmall.home.bean.HomeResutl;
import com.example.administrator.shoppingmall.utils.Constants;
import com.example.administrator.shoppingmall.utils.LogUtil;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/30.
 */

public class RvHomeAdapter extends RecyclerView.Adapter {
    /**
     * 上下文
     */
    private final Context mContext;
    /**
     * 主页面数据
     */
    private final HomeResutl.ResultBean homeResultBean;
    /**
     * 初始化各个item的布局
     */
    private final LayoutInflater mLayoutInflater;
    /**
     * 当前类型
     */
    private int currentType = 0;
    /**
     * 广告条类型
     */
    private static final int BANNER_TYPE = 0;
    /**
     * 频道类型
     */
    private static final int CHANNEL_TYPE = 1;
    /**
     * 活动类型
     */
    private static final int ACT_TYPE = 2;
    /**
     * 秒杀类型
     */
    private static final int SECKILL_TYPE = 3;
    /**
     * 推荐类型
     */
    private static final int RECOMMEND_TYPE = 4;
    /**
     * 热卖类型
     */
    private static final int HOT_TYPE = 5;

    public RvHomeAdapter(Context mContext, HomeResutl.ResultBean homeResultBean) {
        this.mContext = mContext;
        this.homeResultBean = homeResultBean;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }


    /**
     * 相当于BaseAdapter的getView中的创建并返回ViewHolder
     *
     * @param parent
     * @param viewType
     * @return 返回相应的item的ViewHolder
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BANNER_TYPE:
                LogUtil.e("创建并返回ViewHolder");
                return new BannerViewHolder(mContext, mLayoutInflater.inflate(R.layout.banner_viewpager, null));
            default:
                return null;
        }
    }

    /**
     * 相当于BaseAdapter中getView的绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case BANNER_TYPE:
                LogUtil.e("绑定banner数据");
                BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
                bannerViewHolder.setBannerData(homeResultBean.getBanner_info());
                break;
            default:
                break;
        }
    }

    /**
     * 得到item类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER_TYPE:
                currentType = BANNER_TYPE;
                break;

            case CHANNEL_TYPE:
                currentType = CHANNEL_TYPE;
                break;

            case ACT_TYPE:
                currentType = ACT_TYPE;
                break;

            case SECKILL_TYPE:
                currentType = SECKILL_TYPE;
                break;

            case RECOMMEND_TYPE:
                currentType = RECOMMEND_TYPE;
                break;

            case HOT_TYPE:
                currentType = HOT_TYPE;
                break;
        }
        return currentType;
    }

    /**
     * 得到RecyclerView item的总数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        //开发过程中1-6 不然有可能会崩，后面的没有初始化
        return 1;
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private Banner bannerHome;

        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            bannerHome = itemView.findViewById(R.id.banner_home);
        }

        /**
         * 设置banner数据
         *
         * @param banner_info banner的数据
         */
        public void setBannerData(List<HomeResutl.ResultBean.BannerInfoBean> banner_info) {
            List<String> imageURLList = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                String imagerURL = banner_info.get(i).getImage();
                imageURLList.add(Constants.IMAGE_BASE_URL + imagerURL);
            }
            bannerHome.setImages(imageURLList);
            bannerHome.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context.getApplicationContext()).load(path).into(imageView);
                }
            });
            //设置翻页时为手风琴动画
            bannerHome.setBannerAnimation(Transformer.BackgroundToForeground);
            //设置点击事件监听
            bannerHome.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "position ==" + position, Toast.LENGTH_SHORT).show();
                }
            });
            //这个方法必须有！！
            bannerHome.start();
        }
    }
}
