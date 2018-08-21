package com.company.controller.commands;

import com.company.model.entities.User;
import com.company.model.services.guests.SignInService;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        SignInService service = new SignInService();
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login.equals("") || password.equals("")) {
            throw new RuntimeException();
        }

        if (CommandUtils.isLogged(request.getServletContext(), login)) {
            throw new RuntimeException();
        }

        User user = service.findUser(login, password);

        CommandUtils.logUser(request.getServletContext(), user);
        CommandUtils.saveUserInSession(request.getSession(), user);
        if (user.getRole() == User.Role.ADMIN) {
            return request.getContextPath() + "/redirect/admin/admin.jsp";
        } else {
            return request.getContextPath() + "/redirect/user/User.jsp";
        }

    }
}
