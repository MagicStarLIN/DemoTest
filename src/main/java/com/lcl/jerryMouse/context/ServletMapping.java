package com.lcl.jerryMouse.context;

import jakarta.servlet.Servlet;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.regex.Pattern;

/**
 * @author liuchanglin
 * @version 1.0
 * @ClassName: ServletMapping
 * @date 2025/3/26 01:11
 */
@Data
public class ServletMapping extends AbstractMapping{

    public final Servlet servlet;

    public ServletMapping(String urlPattern, Servlet servlet) {
        super(urlPattern);
        this.servlet = servlet;
    }
}
