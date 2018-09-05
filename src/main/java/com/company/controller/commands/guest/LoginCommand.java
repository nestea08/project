package com.company.controller.commands.guest;

import com.company.controller.InputValidator;
import com.company.controller.PagesPaths;
import com.company.controller.commands.Command;
import com.company.controller.exceptions.InvalidEmailException;
import com.company.controller.exceptions.InvalidPasswordException;
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
        catch (Exception e) {
            request.setAttribute("exception", e.getLocalizedMessage());
            return "/WEB-INF/" + PagesPaths.LOGIN;
        }
        GuestCommandUtils.logUser(request.getServletContext(), user);
        GuestCommandUtils.saveUserInSession(request.getSession(), user);
        if (user.getRole() == User.Role.ADMIN) {
            return request.getContextPath() + "/redirect/admin/admin_page";
        } else {
            return request.getContextPath() + "/redirect/user/user_page";
        }

    }

    private User findUser(HttpServletRequest request, String email, String password)
            throws InvalidPasswordException, InvalidEmailException, UnknownUserException {
        SignInService service = new SignInService();
        checkEmailAndPassword(email, password);
        checkLoggedUsers(request, email);
        return service.findUser(email, password);
    }

    private void checkEmailAndPassword(String email, String password)
            throws InvalidPasswordException, InvalidEmailException {
        checkEmail(email);
        checkPassword(password);
    }

    private void checkLoggedUsers(HttpServletRequest request, String email) {
        if (GuestCommandUtils.isLogged(request.getServletContext(), email)) {
            throw new RuntimeException();
        }
    }


    private void checkEmail(String email) throws InvalidEmailException {
        if (InputValidator.strNotMatchesRegex(email, InputValidator.EMAIL_REGEX)) {
            throw new InvalidEmailException(email);
        }
    }

    private void checkPassword(String password) throws InvalidPasswordException {
        if (InputValidator.strNotMatchesRegex(password, InputValidator.PASSWORD_REGEX)) {
            throw new InvalidPasswordException(password);
        }
    }

}
