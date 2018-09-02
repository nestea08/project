package com.company.controller.filters;

import com.company.model.services.LocaleService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

public class LocaleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        String language = req.getParameter("language");
        if (language == null || language.isEmpty()) {
            language = req.getSession().getAttribute("language").toString();
        }
        LocaleService service = new LocaleService();
        service.setLocale(new Locale(language));
        System.out.println(language);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
