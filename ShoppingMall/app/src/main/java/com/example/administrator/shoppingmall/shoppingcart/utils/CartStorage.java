package com.example.administrator.shoppingmall.shoppingcart.utils;

import android.content.Context;
import android.util.SparseArray;

import com.example.administrator.shoppingmall.app.MyApplication;
import com.example.administrator.shoppingmall.home.bean.GoodsBean;
import com.example.administrator.shoppingmall.utils.GsonUtil;
import com.example.administrator.shoppingmall.utils.SpUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/2.
 * 单例模式
 * 在这里 内存相当于：goodsBeanSparseArray
 *        本地相当于：SpUtils
 */

public class CartStorage {
    public static final String JSON_CART = "json_cart";
    /**
     * 购物车实例
     */
    private static CartStorage instance;
    private final Context mContext;

    //性能优于HashMap，在内存中缓存就用它。
    private SparseArray<GoodsBean> goodsBeanSparseArray;

    /**
     * 得到实例
     * @return  购物车实例
     */
    public static CartStorage getInstance() {
        if (instance == null) {
            instance = new CartStorage(MyApplication.getGlobalApplication());
        }
        return instance;
    }

    private CartStorage(Context context){
        this.mContext = context;
        goodsBeanSparseArray = new SparseArray<>(100);
        list2SparseArray();
    }

    /**
     * 从本地读取，加入到SparseArray中
     */
    private void list2SparseArray() {
        List<GoodsBean> goodsBeanList = getAllData();
        //把列表的数据转化昵称SparseArray
        for (int i=0; i<goodsBeanList.size(); i++) {
            GoodsBean goodsBean = goodsBeanList.get(i);
            goodsBeanSparseArray.put(Integer.parseInt(goodsBean.getProduct_id()), goodsBean);
        }
    }

    /**
     * 获取本地所有数据
     * @return
     */
    public List<GoodsBean> getAllData() {
        //1、从本地获取
        String jsonData = SpUtils.getInstance().getString(JSON_CART, "");
        //2、使用Gson转换成列表
        List<GoodsBean> goodsBeanList = new ArrayList<>();
        if (!jsonData.isEmpty()) {
            goodsBeanList = GsonUtil.jsonList2BeanList(jsonData, GoodsBean.class);
        }
        return goodsBeanList;
    }

    /**
     * 添加Goods
     * @param goodsBean
     */
    public void addGoods(GoodsBean goodsBean) {
        //1、添加到内存中
        //如果当前是数据已经存在就改成number递增
        GoodsBean tempData = goodsBeanSparseArray.get(Integer.parseInt(goodsBean.getProduct_id()));
        if (tempData != null) {
            //内存中有这条数据
            tempData.setNumber(tempData.getNumber() + 1);
        } else {
            tempData = goodsBean;
        }
        //2、同步到内存
        goodsBeanSparseArray.put(Integer.parseInt(tempData.getProduct_id()), tempData);
        //3、同步到本地
        saveLocal();
    }

    /**
     * 删除数据
     * @param goodsBean
     */
    public void deleteGoods(GoodsBean goodsBean) {
        //1、添加到内存中
        goodsBeanSparseArray.delete(Integer.parseInt(goodsBean.getProduct_id()));
        saveLocal();
    }

    /**
     * 更新修改数据
     * @param goodsBean
     */
    public void updateGoods(GoodsBean goodsBean) {
        //1、内存中更新
        goodsBeanSparseArray.put(Integer.parseInt(goodsBean.getProduct_id()), goodsBean);

        //2、同步到本地
        saveLocal();
    }


    private void saveLocal() {
        //把SparseArray转换成List
        List<GoodsBean> goodsBeanList = sparse2List();
        //用Gson把列表转化成String类型
        String jsonData = GsonUtil.beanList2JsonList(goodsBeanList);
        //3、把String保存
        SpUtils.getInstance().save(JSON_CART, jsonData);

    }

    private List<GoodsBean> sparse2List() {
        List<GoodsBean> goodsBeanList = new ArrayList<>();
        for (int i=0; i<goodsBeanSparseArray.size(); i++) {
            GoodsBean goodsBean = goodsBeanSparseArray.valueAt(i);
            goodsBeanList.add(goodsBean);
        }
        return goodsBeanList;
    }


}
