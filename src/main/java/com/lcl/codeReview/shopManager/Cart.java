package com.lcl.codeReview.shopManager;

import java.util.ArrayList;
import java.util.List;
/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Cart
 * @date 2021/1/3 8:04 下午
 * 购物车类
 * @author 75196
 *
 */
public class Cart {
    public static List<Commodity> CommodityCartList = new ArrayList<Commodity>();
    public Cart() {
    }
    /**
     * 向购物车内添加商品
     * @param com
     */
    public void add(Commodity com) {
        CommodityCartList.add(com);
        System.out.println("添加成功");
    }
    /**
     * 从购物车内移除商品
     * @param com
     */
    public void remove(Commodity com) {
        CommodityCartList.remove(com);
        System.out.println("商店内已经卖出了"+com.getName());
    }
    /**
     * 结账
     */
    public void checkOut() {
        double ammoutMoney = 0.0;
        for(Commodity com : CommodityCartList) {
            ammoutMoney += com.getPrice();
        }
        System.out.println("总价为："+ammoutMoney);
    }

}
