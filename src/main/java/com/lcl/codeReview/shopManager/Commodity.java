package com.lcl.codeReview.shopManager;

/**
 * @author liuchanglin
 * @author 75196
 * @version 1.0
 * @ClassName: Commodity
 * @date 2021/1/3 8:00 下午
 * 商品类
 */
public class Commodity {

    private String name;
    private double price;
    private String type;

    public Commodity(String name, double price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void showCom() {
        System.out.println(this.name + " " + this.price + " " + this.type);
    }

}
