package com.company.controller.commands.admin;

import com.company.controller.PagesPaths;
import com.company.controller.commands.Command;
import com.company.controller.InputValidator;
import com.company.controller.exceptions.InvalidDescriptionException;
import com.company.controller.exceptions.InvalidTitleException;
import com.company.model.exceptions.NotUniqueActivityException;
import com.company.model.services.admins.ActivityCreationService;

import javax.servlet.http.HttpServletRequest;

public class CreateActivityCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String enTitle = request.getParameter("enTitle");
        String ruTitle = request.getParameter("ruTitle");
        String enDescription = request.getParameter("enDescription");
        String ruDescription = request.getParameter("ruDescription");
        try {
            checkActivityCreation(enTitle, ruTitle, enDescription, ruDescription);
        }
        catch (Exception e) {
            request.setAttribute("exception", e.getLocalizedMessage());
            return "/WEB-INF/" + PagesPaths.CREATE_ACTIVITY;
        }
        return request.getContextPath() + "/redirect/admin/activity_created_page";
    }

    private void checkActivityCreation(String enTitle, String ruTitle,
                                       String enDescription, String ruDescription)
            throws Exception {
        checkEnTitleValidity(enTitle);
        checkRuTitleValidity(ruTitle);
        checkEnDescriptionValidity(enDescription);
        checkRuDescriptionValidity(ruDescription);
        createActivity(enTitle, ruTitle, enDescription, ruDescription);
    }

    private void checkEnTitleValidity(String enTitle) throws InvalidTitleException {
        if (InputValidator.strNotMatchesRegex(enTitle, InputValidator.EN_TITLE_REGEX)) {
            throw new InvalidTitleException(enTitle);
        }
    }

    private void checkRuTitleValidity(String ruTitle) throws InvalidTitleException {
        if (InputValidator.strNotMatchesRegex(ruTitle, InputValidator.RU_TITLE_REGEX)) {
            throw new InvalidTitleException(ruTitle);
        }
    }

    private void checkEnDescriptionValidity(String enDescription) throws InvalidDescriptionException {
        if (InputValidator.strNotMatchesRegex(enDescription, InputValidator.EN_DESCRIPTION_REGEX)) {
            throw new InvalidDescriptionException(enDescription);
        }
    }

    private void checkRuDescriptionValidity(String ruDescription) throws InvalidDescriptionException {
        if (InputValidator.strNotMatchesRegex(ruDescription, InputValidator.RU_DESCRIPTION_REGEX)) {
            throw new InvalidDescriptionException(ruDescription);
        }
    }

    private void createActivity(String enTitle, String ruTitle,
                                String enDescription, String ruDescription)
            throws NotUniqueActivityException {
        ActivityCreationService service = new ActivityCreationService();
        service.createActivity(enTitle, ruTitle, enDescription, ruDescription);
    }
}
