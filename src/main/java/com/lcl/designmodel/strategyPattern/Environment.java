package com.lcl.designmodel.strategyPattern;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: Enviroument
 * @Description: 环境角色
 * @date 2019-07-25 15:13
 */
public class Environment {
    private Strategy strategy;

    public Environment(Strategy strategy) {
        this.strategy = strategy;
    }

    public int calulate(int a, int b) {
        return strategy.calc(a, b);
    }
}
