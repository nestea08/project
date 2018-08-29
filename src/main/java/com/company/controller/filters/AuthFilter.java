package com.company.controller.filters;

import com.company.controller.commands.Command;
import com.company.controller.commands.CommandUtils;
import com.company.controller.commands.LogoutCommand;
import com.company.model.entities.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.company.model.entities.User.Role.GUEST;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        User.Role role = (User.Role)req.getSession().getAttribute("role");
        role = role == null ? GUEST : role;
        if (hasNotNecessaryAccess(req.getRequestURI(), role)) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }
        else if (authorizedUserReturnsToGuestPages(req.getRequestURI(), role)) {
            Command command = new LogoutCommand();
            String path = command.execute(req).replace("redirect/","");
            resp.sendRedirect(path);
        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
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

    private boolean authorizedUserReturnsToGuestPages(String uri, User.Role role) {
        return adminReturnsToGuestPages(uri, role) || userReturnsToGuestPages(uri, role);
    }

    private boolean userReturnsToGuestPages(String uri, User.Role role) {
        return !uri.contains("/user/") && role == User.Role.USER;
    }

    private boolean adminReturnsToGuestPages(String uri, User.Role role) {
        return !uri.contains("/admin/") && role == User.Role.ADMIN;
    }

    @Override
    public void destroy() {

    }
}
