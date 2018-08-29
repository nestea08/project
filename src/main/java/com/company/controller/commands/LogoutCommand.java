package com.company.controller.commands;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getSession().getAttribute("email").toString();
        CommandUtils.removeUserFromSession(request.getSession());
        CommandUtils.removeLoggedUser(request.getServletContext(), email);
        return request.getContextPath() + "/redirect/index.jsp";
    }
}
