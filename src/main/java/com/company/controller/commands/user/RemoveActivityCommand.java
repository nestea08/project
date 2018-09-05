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
            service.saveTrackerRequest(userId, activityId, Request.RequestType.REMOVE);
        } catch (DuplicateRequestException e) {
            return UserCommandUtils.setExceptionAttributeAndGetRedirectPath(e, request);
        }
        return request.getContextPath() + "/redirect/user/activity_removed_page";
    }
}
