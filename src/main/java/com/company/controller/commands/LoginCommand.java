package com.company.controller.commands;

import com.company.controller.commands.exceptions.NotValidEmailException;
import com.company.controller.commands.exceptions.NotValidPasswordException;
import com.company.model.entities.User;
import com.company.model.exceptions.UnknownUserException;
import com.company.model.services.guests.SignInService;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user;
        try {
            user = findUser(request, email, password);
        }
        catch (NotValidEmailException | NotValidPasswordException | UnknownUserException e) {
            request.setAttribute("exception", e.getMessage());
            return "/login.jsp";
        }
        CommandUtils.logUser(request.getServletContext(), user);
        CommandUtils.saveUserInSession(request.getSession(), user);
        if (user.getRole() == User.Role.ADMIN) {
            return request.getContextPath() + "/redirect/admin/admin.jsp";
        } else {
            return request.getContextPath() + "/redirect/user/User.jsp";
        }

    }

    private User findUser(HttpServletRequest request, String email, String password)
            throws NotValidPasswordException, NotValidEmailException, UnknownUserException {
        SignInService service = new SignInService();
        checkEmailAndPassword(email, password);
        checkLoggedUsers(request, email);
        return service.findUser(email, password);
    }

    private void checkEmailAndPassword(String email, String password)
            throws NotValidPasswordException, NotValidEmailException {
        checkEmail(email);
        checkPassword(password);
    }

    private void checkLoggedUsers(HttpServletRequest request, String email) {
        if (CommandUtils.isLogged(request.getServletContext(), email)) {
            throw new RuntimeException();
        }
    }


    private void checkEmail(String email) throws NotValidEmailException {
        if (email == null || InputValidator.strNotMatchesRegex(email, InputValidator.EMAIL_REGEX)) {
            throw new NotValidEmailException(email);
        }
    }

    private void checkPassword(String password) throws NotValidPasswordException {
        if (password == null || InputValidator.strNotMatchesRegex(password, InputValidator.PASSWORD_REGEX)) {
            throw new NotValidPasswordException(password);
        }
    }

}
