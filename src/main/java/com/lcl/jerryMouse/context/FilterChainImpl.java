package com.lcl.jerryMouse.context;

import jakarta.servlet.*;

import java.io.IOException;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: FilterChainImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2025/4/2 01:38
 */
public class FilterChainImpl implements FilterChain {

    final Filter[] filters;
    final Servlet servlet;
    final int total;
    int index = 0;

    public FilterChainImpl(Filter[] filters, Servlet servlet) {
        this.filters = filters;
        this.servlet = servlet;
        this.total = filters.length;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        if (index < total) {
            int current = index;
            index++;
            filters[current].doFilter(request, response, this);
        } else {
            servlet.service(request, response);
        }
    }

}