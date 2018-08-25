package com.company.controller.commands;

import com.company.controller.commands.exceptions.NotValidEmailException;
import com.company.controller.commands.exceptions.NotValidNicknameException;
import com.company.controller.commands.exceptions.NotValidPasswordException;
import com.company.model.entities.User;
import com.company.model.services.guests.SignUpService;

import javax.servlet.http.HttpServletRequest;

public class SignupCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        SignUpService service = new SignUpService();
        User user;
        try {
            checkInput(nickname, email, password);
            user = service.createUser(nickname, email, password);
        } catch (Exception e) {
            request.setAttribute("exception", e.getLocalizedMessage());
            return "/signUp.jsp";
        }
        CommandUtils.logUser(request.getServletContext(), user);
        CommandUtils.saveUserInSession(request.getSession(), user);
        return request.getContextPath() + "/redirect/user/User.jsp";
    }

    private void checkInput(String nickname, String email, String password)
            throws NotValidEmailException, NotValidNicknameException, NotValidPasswordException {
        checkNickname(nickname);
        checkEmail(email);
        checkPassword(password);
    }

    private void checkNickname(String nickname) throws NotValidNicknameException {
        if (InputValidator.strNotMatchesRegex(nickname, InputValidator.NICKNAME_REGEX)) {
            throw new NotValidNicknameException(nickname);
        }
    }

    private void checkEmail(String email) throws NotValidEmailException {
        if (InputValidator.strNotMatchesRegex(email, InputValidator.EMAIL_REGEX)) {
            throw new NotValidEmailException(email);
        }
    }

    private void checkPassword(String password) throws NotValidPasswordException {
        if (InputValidator.strNotMatchesRegex(password, InputValidator.PASSWORD_REGEX)) {
            throw new NotValidPasswordException(password);
        }
    }
}
