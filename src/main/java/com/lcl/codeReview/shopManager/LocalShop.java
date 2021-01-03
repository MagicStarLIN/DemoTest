package com.lcl.codeReview.shopManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: LocalShop
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2021/1/3 8:02 下午
 */
public class LocalShop extends abstractShop {
    /**
     * 建立两个商品柜台列表 存储商品
     * 1.杂物柜台 Sundries
     * 2.食品柜台 food
     */
    static {
        CommodityList.add(new Commodity("毛巾", 8.0, "Sundries"));
        CommodityList.add(new Commodity("苹果", 3.0, "food"));
        CommodityList.add(new Commodity("香蕉", 5.0, "food"));
        CommodityList.add(new Commodity("西瓜", 8.0, "food"));
        CommodityList.add(new Commodity("梨", 2.0, "food"));
        CommodityList.add(new Commodity("电池", 5.0, "Sundries"));
        CommodityList.add(new Commodity("蜡烛", 3.0, "Sundries"));
        CommodityList.add(new Commodity("手电", 10.0, "Sundries"));
    }

    //杂物柜台
    public LocalShop() {
        System.out.println("欢迎光临");
    }

    public void show() {
        for (Commodity l : CommodityList) {
            l.showCom();
        }
    }

    @Override
    public void open() {
        System.out.println("开业大吉");
    }

    @Override
    public void addCommodity(Commodity com) {
        if (com.getType().equals("food")) {
            FoodList.add(com);
        } else if (com.getType().equals("Sundries")) {
            SundriesList.add(com);
        }
    }

    @Override
    public void close() {
        if (CommodityList == null) {
            System.out.println("商店卖断货 关门了");
        }
    }

    @Override
    public void typeSet() {
        for (Commodity l : CommodityList) {
            if (l.getType().equalsIgnoreCase("food")) {
                FoodList.add(l);
            } else if (l.getType().equalsIgnoreCase("Sundries")) {
                SundriesList.add(l);
            }
        }
    }

    @Override
    public void typeSet(Commodity com) {

    }
}
