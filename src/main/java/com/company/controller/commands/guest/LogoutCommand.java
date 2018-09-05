package com.company.controller.commands.guest;

import com.company.controller.PagesPaths;
import com.company.controller.commands.Command;
import com.company.controller.commands.CommandUtils;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getSession().getAttribute("email").toString();
        GuestCommandUtils.removeUserFromSession(request.getSession());
        GuestCommandUtils.removeLoggedUser(request.getServletContext(), email);
        return request.getContextPath() + "/redirect/" + PagesPaths.INDEX;
    }
}
