package com.company.controller.commands;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command{
    @Override
    public String execute(HttpServletRequest request) {
        String nickname = request.getSession().getAttribute("user").toString();
        CommandUtils.removeUserFromSession(request.getSession());
        CommandUtils.removeLoggedUser(request.getServletContext(), nickname);
        return request.getContextPath() + "/redirect/index.jsp";
    }
}
