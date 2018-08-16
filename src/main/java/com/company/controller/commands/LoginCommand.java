package com.company.controller.commands;

import com.company.model.entities.User;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login.equals("") || password.equals("")) {
            throw new RuntimeException();
        }

        if (CommandUtils.isLogged(request.getServletContext(), login)) {
            throw new RuntimeException();
        }

        CommandUtils.logUser(request.getServletContext(), login);

        if (login.equalsIgnoreCase("admin")) {
            CommandUtils.saveUserInSession(request.getSession(), login, User.Role.ADMIN);
            request.setAttribute("check", "ok");
            return request.getContextPath() + "/admin/admin.jsp";
        } else if (login.equalsIgnoreCase("user")) {
            CommandUtils.saveUserInSession(request.getSession(), login, User.Role.USER);
            return request.getContextPath() + "/user/User.jsp";
        } else {
            return request.getContextPath() + "/index.jsp";
        }

    }
}
