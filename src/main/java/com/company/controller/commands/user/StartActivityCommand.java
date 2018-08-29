package com.company.controller.commands.user;

import com.company.controller.commands.Command;
import com.company.model.entities.Request;
import com.company.model.exceptions.DuplicateRequestException;
import com.company.model.services.users.RequestsSaverService;

import javax.servlet.http.HttpServletRequest;

public class StartActivityCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        int activityId = Integer.parseInt(request.getParameter("id"));
        int userId = UserCommandUtils.getUserIdFromSession(request.getSession());
        RequestsSaverService service = new RequestsSaverService();
        try {
            service.saveTrackerUserRequest(userId, activityId, Request.RequestType.ADD);
        } catch (DuplicateRequestException e) {
            request.getSession().setAttribute("exception", e.getLocalizedMessage());
            return request.getContextPath() + "/redirect/user/user_exception.jsp";
        }
        return request.getContextPath() + "/redirect/user/activity_started.jsp";
    }
}
