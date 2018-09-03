package com.company.controller.commands.user;

import com.company.controller.commands.Command;
import com.company.model.entities.interfaces.TimeTracking;
import com.company.model.services.users.TimeTrackingService;

import javax.servlet.http.HttpServletRequest;

public class ActivityTrackingCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        int activityId = Integer.parseInt(request.getParameter("id"));
        int userId = UserCommandUtils.getUserIdFromSession(request.getSession());
        TimeTrackingService service = new TimeTrackingService();
        TimeTracking timeTracking;
        try {
            timeTracking = service.getTrackingsById(userId, activityId);
        } catch (Exception e) {
           return UserCommandUtils.setExceptionAttributeAndGetRedirectPath(e, request);
        }
        UserCommandUtils.saveTrackedItemInSession(request.getSession(), timeTracking);
        return request.getContextPath() + "/redirect/user/time_tracking";
    }

}
