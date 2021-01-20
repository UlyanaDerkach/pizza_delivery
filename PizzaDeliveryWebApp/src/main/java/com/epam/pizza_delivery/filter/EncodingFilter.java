package com.epam.pizza_delivery.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EncodingFilter implements Filter {
    private static final String ENCODING = "encoding";
    private static final String CONTENT_TYPE = "contentType";
    private static final String MULTIPART_CONTENT_TYPE = "multipart/form-data";
    private String encoding;
    private String contentType;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        servletResponse.setContentType(contentType);
        servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);

    }



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter(ENCODING);
        contentType = filterConfig.getInitParameter(CONTENT_TYPE);
        if (encoding == null || contentType == null) {
            encoding = "UTF-8";
            contentType = "text/html";
        }
        if(contentType != null && contentType.startsWith(MULTIPART_CONTENT_TYPE)){
            contentType = MULTIPART_CONTENT_TYPE;
        }

    }
    @Override
    public void destroy() {

    }
}
