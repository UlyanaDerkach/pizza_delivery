package com.epam.pizza_delivery.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private static final String ENCODING_UTF_8 =  "UTF-8";
    private static final String CONTENT_TYPE_TEXT_HTML = "text/html";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        servletResponse.setContentType(CONTENT_TYPE_TEXT_HTML);
        servletRequest.setCharacterEncoding(ENCODING_UTF_8);
        filterChain.doFilter(servletRequest, servletResponse);

    }



    @Override
    public void init(FilterConfig filterConfig)  {

    }
    @Override
    public void destroy() {

    }
}
