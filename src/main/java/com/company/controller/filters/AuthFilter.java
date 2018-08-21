package com.company.controller.filters;

import com.company.model.entities.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        User.Role role = (User.Role)req.getSession().getAttribute("role");
        if (hasNotNecessaryAccess(req.getRequestURI(), role)) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean hasNotNecessaryAccess(String uri, User.Role role) {
        return tryingToBecomeAdmin(uri, role) || tryingToBecomeUser(uri, role);
    }

    private boolean tryingToBecomeAdmin(String uri, User.Role role) {
        return uri.contains("/admin/") && role != User.Role.ADMIN;
    }

    private boolean tryingToBecomeUser(String uri, User.Role role) {
        return uri.contains("/user/") && role != User.Role.USER;
    }

    @Override
    public void destroy() {

    }
}
