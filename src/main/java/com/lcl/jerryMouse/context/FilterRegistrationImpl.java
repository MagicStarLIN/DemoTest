package com.lcl.jerryMouse.context;

import jakarta.servlet.*;

import java.util.*;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: FilterRegistrationImpl
 * @date 2025/4/2 00:58
 */
public class FilterRegistrationImpl implements FilterRegistration.Dynamic {

    final ServletContext servletContext;
    final String name;
    final Filter filter;

    final InitParameters initParameters = new InitParameters();
    final List<String> urlPatterns = new ArrayList<>(4);
    boolean initialized = false;

    public FilterRegistrationImpl(ServletContextImpl servletContext, String name, Filter filter) {
        this.servletContext = servletContext;
        this.name = name;
        this.filter = filter;
    }


    @Override
    public void addMappingForServletNames(EnumSet<DispatcherType> enumSet, boolean b, String... strings) {

    }

    @Override
    public Collection<String> getServletNameMappings() {
        return List.of();
    }

    @Override
    public void addMappingForUrlPatterns(EnumSet<DispatcherType> enumSet, boolean b, String... strings) {

    }

    @Override
    public Collection<String> getUrlPatternMappings() {
        return List.of();
    }

    @Override
    public void setAsyncSupported(boolean b) {

    }

    public FilterConfig getFilterConfig() {
        return new FilterConfig() {
            @Override
            public String getFilterName() {
                return FilterRegistrationImpl.this.name;
            }

            @Override
            public ServletContext getServletContext() {
                return FilterRegistrationImpl.this.servletContext;
            }

            @Override
            public String getInitParameter(String name) {
                return FilterRegistrationImpl.this.initParameters.getInitParameter(name);
            }

            @Override
            public Enumeration<String> getInitParameterNames() {
                return FilterRegistrationImpl.this.initParameters.getInitParameterNames();
            }
        };
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getClassName() {
        return "";
    }

    @Override
    public boolean setInitParameter(String s, String s1) {
        return false;
    }

    @Override
    public String getInitParameter(String s) {
        return "";
    }

    @Override
    public Set<String> setInitParameters(Map<String, String> map) {
        return Set.of();
    }

    @Override
    public Map<String, String> getInitParameters() {
        return Map.of();
    }
}
