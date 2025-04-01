package com.lcl.jerryMouse.context;

import jakarta.servlet.Filter;

import java.util.regex.Pattern;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: FilterMapping
 * @date 2025/4/2 01:20
 */
public class FilterMapping extends AbstractMapping {

    final Pattern pattern;
    final Filter filter;

    public FilterMapping(String urlPattern, Filter filter) {
        super(urlPattern);
        this.pattern = Pattern.compile(urlPattern);
        this.filter = filter;
    }
}
