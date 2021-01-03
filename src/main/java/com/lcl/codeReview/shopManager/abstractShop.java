package com.lcl.codeReview.shopManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: abstractShop
 * @date 2021/1/3 8:01 下午
 */
public abstract class abstractShop implements Shop {

    //商店的收款柜台 营业额
    public static final double MONEY = 0.0;
    //货物总列表
    protected static List<Commodity> CommodityList = new ArrayList<Commodity>();
    //杂货柜台
    protected static List<Commodity> SundriesList = new ArrayList<Commodity>();
    //食品柜台
    protected static List<Commodity> FoodList = new ArrayList<Commodity>();


    public void typeSet() {
        // TODO Auto-generated method stub

    }

}