package com.company.controller.commands.admin;

import com.company.controller.commands.Command;
import com.company.controller.commands.InputValidator;
import com.company.controller.commands.exceptions.NotValidDescriptionException;
import com.company.controller.commands.exceptions.NotValidTitleException;
import com.company.model.exceptions.NotUniqueActivityException;
import com.company.model.services.admins.ActivityCreationService;

import javax.servlet.http.HttpServletRequest;

public class CreateActivityCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        try {
            checkActivityCreation(title,description);
        }
        catch (Exception e) {
            request.setAttribute("exception", e.getLocalizedMessage());
            return "/admin/create_activity.jsp";
        }
        return request.getContextPath() + "/redirect/admin/activity_created.jsp";
    }

    private void checkActivityCreation(String title, String description)
            throws NotValidTitleException, NotValidDescriptionException, NotUniqueActivityException {
        checkTitleValidity(title);
        checkDescriptionValidity(description);
        createActivity(title, description);
    }

    private void checkTitleValidity(String title) throws NotValidTitleException {
        if (InputValidator.strNotMatchesRegex(title, InputValidator.TITLE_REGEX)) {
            throw new NotValidTitleException(title);
        }
    }

    private void checkDescriptionValidity(String description) throws NotValidDescriptionException {
        if (InputValidator.strNotMatchesRegex(description, InputValidator.DESCRIPTION_REGEX)) {
            throw new NotValidDescriptionException(description);
        }
    }

    private void createActivity(String title, String description) throws NotUniqueActivityException {
        ActivityCreationService service = new ActivityCreationService();
        service.createActivity(title, description);
    }
}
