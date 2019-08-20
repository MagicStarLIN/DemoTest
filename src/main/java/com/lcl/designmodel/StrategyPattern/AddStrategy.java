package com.lcl.designmodel.StrategyPattern;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: AddStrategy
 * @Description: 加法策略
 * @date 2019-07-25 15:09
 */
public class AddStrategy implements Strategy {

    @Override
    public int calc(int num1, int num2) {
        return num1 + num2;
    }
}
