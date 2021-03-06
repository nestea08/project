package com.company.controller.commands.guest;

import com.company.controller.InputValidator;
import com.company.controller.PagesPaths;
import com.company.controller.commands.Command;
import com.company.controller.commands.CommandUtils;
import com.company.controller.exceptions.InvalidEmailException;
import com.company.controller.exceptions.InvalidNicknameException;
import com.company.controller.exceptions.InvalidPasswordException;
import com.company.model.entities.User;
import com.company.model.services.guests.SignUpService;

import javax.servlet.http.HttpServletRequest;

public class SignUpCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        SignUpService service = new SignUpService();
        try {
            checkInput(nickname, email, password);
            service.createUser(nickname, email, password);
        } catch (Exception e) {
            request.setAttribute("exception", e.getLocalizedMessage());
            return "/WEB-INF/" + PagesPaths.SIGN_UP;
        }
        return new LoginCommand().execute(request);
    }

    private void checkInput(String nickname, String email, String password)
            throws InvalidEmailException, InvalidNicknameException, InvalidPasswordException {
        checkNickname(nickname);
        checkEmail(email);
        checkPassword(password);
    }

    private void checkNickname(String nickname) throws InvalidNicknameException {
        if (InputValidator.strNotMatchesRegex(nickname, InputValidator.NICKNAME_REGEX)) {
            throw new InvalidNicknameException(nickname);
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
