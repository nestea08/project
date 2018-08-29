package com.company.controller.commands.user;

import com.company.controller.commands.Command;
import com.company.model.services.users.TimeTrackingService;

import javax.servlet.http.HttpServletRequest;

public class FinishActivityCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        TimeTrackingService service = new TimeTrackingService();
        int activityId = Integer.parseInt(request.getParameter("id"));
        int userId = UserCommandUtils.getUserIdFromSession(request.getSession());
        try {
            service.finishTracking(userId, activityId);
        }
        catch (Exception e) {
            return UserCommandUtils.setExceptionAttributeAndGetRedirectPath(e, request);
        }
        return request.getContextPath() + "/redirect/user/activities";
    }
}
