package com.company.controller.commands.user;

import com.company.controller.commands.Command;
import com.company.model.services.users.TimeTrackingService;

import javax.servlet.http.HttpServletRequest;

public class FinishActivityCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        TimeTrackingService service = new TimeTrackingService();
        int activityId = Integer.parseInt(request.getParameter("id"));
        int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
        service.finishTracking(userId, activityId);
        return request.getContextPath() + "/redirect/user/activities";
    }
}
