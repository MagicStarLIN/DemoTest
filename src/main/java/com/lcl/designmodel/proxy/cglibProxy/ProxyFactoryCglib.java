package com.lcl.designmodel.proxy.cglibProxy;


import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 Cglib代理类
 */
public class ProxyFactoryCglib implements MethodInterceptor {
    //维护一个目标对象
    private Object target;
    public ProxyFactoryCglib(Object target){
        this.target = target;
    }
    //给目标对象创建一个代理对象
    public Object getProxyInstance() {
        //工具类
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(target.getClass());
        //设置回调函数
        enhancer.setCallback(this);
        //创建子类对象即代理对象

        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开启事务");
        Object invoke = method.invoke(target, objects);
        System.out.println("提交事务");
        return invoke;
    }
    public static void main(String[] args) {

        Student student = new Student();
        Student studentproxy  = (Student)new ProxyFactoryCglib(student).getProxyInstance();
        studentproxy.print();
    }

}

/**
 * 无接口被代理类
 */
class Student{
    public void print(){
        System.out.println("无接口执行了。。");
    }
}


