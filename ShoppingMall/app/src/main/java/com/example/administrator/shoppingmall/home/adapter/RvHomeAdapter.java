package com.example.administrator.shoppingmall.home.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.shoppingmall.R;
import com.example.administrator.shoppingmall.home.bean.HomeResutl;
import com.example.administrator.shoppingmall.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
                return new BannerViewHolder(mContext, mLayoutInflater.inflate(R.layout.banner_item, null));

            case CHANNEL_TYPE:
                return new ChannelViewHolder(mContext, mLayoutInflater.inflate(R.layout.channel_item, null));

            case ACT_TYPE:
                return new ActViewHolder(mContext, mLayoutInflater.inflate(R.layout.act_item, null));

            case SECKILL_TYPE:
                return new SeckillViewHolder(mContext, mLayoutInflater.inflate(R.layout.seckill_item, null));

            case RECOMMEND_TYPE:
                return new RecommendViewHolder(mContext, mLayoutInflater.inflate(R.layout.recommend_item, null));

            case HOT_TYPE:
                return new HotViewHolder(mContext, mLayoutInflater.inflate(R.layout.hot_item, null));

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
                BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
                bannerViewHolder.setBannerData(homeResultBean.getBanner_info());
                break;

            case CHANNEL_TYPE:
                ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
                channelViewHolder.setChannelData(homeResultBean.getChannel_info());
                break;

            case ACT_TYPE:
                ActViewHolder actViewHolder = (ActViewHolder) holder;
                actViewHolder.setActData(homeResultBean.getAct_info());
                break;

            case SECKILL_TYPE:
                SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
                seckillViewHolder.setSecKillData(homeResultBean.getSeckill_info());
                break;

            case RECOMMEND_TYPE:
                RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
                recommendViewHolder.setReconmmentData(homeResultBean.getRecommend_info());
                break;

            case HOT_TYPE:
                HotViewHolder hotViewHolder = (HotViewHolder) holder;
                hotViewHolder.setHotData(homeResultBean.getHot_info());
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
        return 6;
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
            bannerHome.setBannerAnimation(Transformer.FlipVertical);
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

    class ChannelViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private GridView gvChannel;
        private GvChannelAdapter gvChannelAdapter;

        public ChannelViewHolder(final Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            gvChannel = itemView.findViewById(R.id.gv_channel);
            gvChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(mContext, "position == " + i, Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setChannelData(List<HomeResutl.ResultBean.ChannelInfoBean> channel_info) {
            //得到数据
            //设置GridView适配器
            gvChannelAdapter = new GvChannelAdapter(mContext, channel_info);
            gvChannel.setAdapter(gvChannelAdapter);
        }
    }

    class ActViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private Banner bannerAct;

        public ActViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            bannerAct = itemView.findViewById(R.id.banner_act);
        }

        public void setActData(List<HomeResutl.ResultBean.ActInfoBean> act_info) {
            List<String> imageURLList = new ArrayList<>();
            for (int i = 0; i < act_info.size(); i++) {
                String imagerURL = act_info.get(i).getIcon_url();
                imageURLList.add(Constants.IMAGE_BASE_URL + imagerURL);
            }
            bannerAct.setImages(imageURLList);
            bannerAct.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context.getApplicationContext()).load(path).into(imageView);
                }
            });

            //设置翻页时为手风琴动画
            bannerAct.setBannerAnimation(Transformer.RotateUp);
            //设置点击事件监听
            bannerAct.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "position ==" + position, Toast.LENGTH_SHORT).show();
                }
            });
            //这个方法必须有！！
            bannerAct.start();
        }
    }

    class SeckillViewHolder extends RecyclerView.ViewHolder {
        private long seckillPeriod;
        private Context mContext;
        private TextView tv_time_seckill;
        private TextView tv_more_seckill;
        private RecyclerView rv_secklill;
        private RvSeckillAdapter rvSeckillAdapter;
        private Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                removeCallbacksAndMessages(null);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                seckillPeriod -= 1000;          //是－1000不是1！！1000毫秒才是1秒
                String temp = simpleDateFormat.format(new Date(seckillPeriod));
                tv_time_seckill.setText(temp);
                handler.sendEmptyMessageDelayed(1, 1000);
                if (seckillPeriod <= 0) {
                    removeCallbacksAndMessages(null);
                }
            }
        };

        public SeckillViewHolder(final Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            tv_time_seckill = itemView.findViewById(R.id.tv_time_seckill);
            tv_more_seckill = itemView.findViewById(R.id.tv_more_seckill);
            rv_secklill = itemView.findViewById(R.id.rv_secklill);

            tv_more_seckill.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "加载更多！", Toast.LENGTH_SHORT).show();
                }
            });
            seckillPeriod = Long.valueOf(homeResultBean.getSeckill_info().getEnd_time()) - Long.valueOf(homeResultBean.getSeckill_info().getStart_time());
            handler.sendEmptyMessageDelayed(1, 1000);
        }

        public void setSecKillData(HomeResutl.ResultBean.SeckillInfoBean seckill_info) {
            //1、得到数据
            //2、设置数据：文本和RecyclerView数据
            //设置适配器
            rvSeckillAdapter = new RvSeckillAdapter(mContext, seckill_info.getList());
            rv_secklill.setAdapter(rvSeckillAdapter);
            //设置布局管理,第三个参数为是否是倒序
            rv_secklill.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            rvSeckillAdapter.setOnSeckillRecyclerView(new RvSeckillAdapter.OnSeckillRecyclerView() {
                @Override
                public void onItemClickListenter(int position) {
                    Toast.makeText(mContext, "position == " + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private TextView tv_more_recommend;
        private GridView gv_recommend;
        private GvRecommendAdapter gvRecommendAdapter;

        public RecommendViewHolder(final Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            tv_more_recommend = itemView.findViewById(R.id.tv_more_recommend);
            gv_recommend = itemView.findViewById(R.id.gv_recommend);
        }

        public void setReconmmentData(List<HomeResutl.ResultBean.RecommendInfoBean> reconmmentData) {
            gvRecommendAdapter = new GvRecommendAdapter(mContext, reconmmentData);
            gv_recommend.setAdapter(gvRecommendAdapter);
            gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(mContext, "position == " + i, Toast.LENGTH_SHORT).show();
                }
            });
            tv_more_recommend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "加载更多", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class HotViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        private RvHotAdapter rvHotAdapter;
        private TextView tv_more_hot;
        private RecyclerView rv_hot;

        public HotViewHolder(final Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            tv_more_hot = itemView.findViewById(R.id.tv_more_hot);
            rv_hot = itemView.findViewById(R.id.rv_hot);
        }

        public void setHotData(List<HomeResutl.ResultBean.HotInfoBean> hot_info) {
            rvHotAdapter = new RvHotAdapter(mContext, hot_info);
            rv_hot.setAdapter(rvHotAdapter);
            rv_hot.setLayoutManager(new GridLayoutManager(mContext, 2));
            rvHotAdapter.setOnHotRecyclerViewClick(new RvHotAdapter.OnHotRecyclerViewClick() {
                @Override
                public void onItemClickListenter(int position) {
                    Toast.makeText(mContext, "position == " + position, Toast.LENGTH_SHORT).show();
                }
            });
            tv_more_hot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "加载更多", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}
