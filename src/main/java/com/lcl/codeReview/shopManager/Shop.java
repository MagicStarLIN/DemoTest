package com.lcl.codeReview.shopManager;

/**
 * 接口 商店 提供方法  开业 进货 关门 和商品归类
 *
 * @author 75196
 */
public interface Shop {
    /**
     * 开业
     */
    void open();

    /**
     * 进货
     */
    void addCommodity(Commodity com);

    /**
     * 售空后关门
     */
    void close();

    /**
     * 归类
     */
    void typeSet(Commodity com);
}
