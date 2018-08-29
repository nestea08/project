package com.company.controller.commands.user;

import com.company.controller.commands.Command;
import com.company.model.entities.Request;
import com.company.model.exceptions.DuplicateRequestException;
import com.company.model.services.users.RequestsSaverService;

import javax.servlet.http.HttpServletRequest;

public class RemoveActivityCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        RequestsSaverService service = new RequestsSaverService();
        int activityId = Integer.parseInt(request.getParameter("id"));
        int userId = UserCommandUtils.getUserIdFromSession(request.getSession());
        try {
            service.saveTrackerUserRequest(userId, activityId, Request.RequestType.REMOVE);
        } catch (DuplicateRequestException e) {
            request.getSession().setAttribute("exception", e.getLocalizedMessage());
            return request.getContextPath() + "/redirect/user/user_exception.jsp";
        }
        return request.getContextPath() + "/redirect/user/remove.jsp";
    }
}
