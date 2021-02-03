package com.epam.pizza_delivery.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

import static com.epam.pizza_delivery.util.constants.ParameterConstants.LANGUAGE;

public class LocaleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        if (req.getParameter(LANGUAGE) != null) {
            req.getSession().setAttribute(LANGUAGE, req.getParameter(LANGUAGE));
        } else{
            req.getSession().setAttribute(LANGUAGE, Locale.getDefault().getLanguage());
        }


        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
