package com.lcl.designmodel.strategyPattern;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: SubtractStrategy
 * @Description: 减法策略
 * @date 2019-07-25 15:10
 */
public class SubtractStrategy implements Strategy {
    @Override
    public int calc(int num1, int num2) {
        return num1 - num2;
    }
}
